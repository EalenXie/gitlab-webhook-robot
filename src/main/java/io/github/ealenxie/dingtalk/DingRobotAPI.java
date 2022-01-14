package io.github.ealenxie.dingtalk;

import io.github.ealenxie.dingtalk.message.DingRobotMessage;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by EalenXie on 2021/12/1 14:43
 * https://open.dingtalk.com/document/robots/custom-robot-access
 * 钉钉机器人 API
 */
public class DingRobotAPI {

    private DingRobotAPI() {
    }

    private static final Logger log = LoggerFactory.getLogger(DingRobotAPI.class);
    private static final HttpHeaders HEADERS;
    public static final RestTemplate REST_TEMPLATE;
    private static final String DEFAULT_API_URL = "https://oapi.dingtalk.com/robot/send";

    static {
        // 钉钉请求头为 application/json
        HEADERS = new HttpHeaders();
        HEADERS.setContentType(MediaType.APPLICATION_JSON);
        // 默认连接超时 15秒
        final int CONNECT_TIMEOUT = 15 * 1000;
        //默认读取超时 30秒
        final int READ_TIMEOUT = 30 * 1000;
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());
        // 同路由并发数
        connectionManager.setDefaultMaxPerRoute(20);
        // 最大连接数
        connectionManager.setMaxTotal(50);
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setHttpClient(closeableHttpClient);
        REST_TEMPLATE = new RestTemplate(factory);
        List<HttpMessageConverter<?>> converters = REST_TEMPLATE.getMessageConverters();
        // StringHttpMessage 序列化采用UTF-8
        for (int i = 0; i < converters.size(); i++) {
            if (converters.get(i) instanceof StringHttpMessageConverter) {
                StringHttpMessageConverter converter = (StringHttpMessageConverter) converters.get(i);
                converter.setDefaultCharset(StandardCharsets.UTF_8);
                converters.set(i, converter);
                break;
            }
        }
        REST_TEMPLATE.setMessageConverters(converters);
    }


    /**
     * 钉钉接口签名
     *
     * @param timestamp 时间戳
     * @param secret    签名密钥
     * @return 签名
     */
    public static String sign(long timestamp, String secret) {
        String stringToSign = timestamp + "\n" + secret;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8.name());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /**
     * 调用钉钉机器人接口
     *
     * @param message     钉钉机器人消息
     * @param accessToken accessToken
     * @param signKey     signKey
     */
    public static ResponseEntity<String> callDingRobot(DingRobotMessage message, String accessToken, String signKey) {
        return callDingRobot(DEFAULT_API_URL, message, accessToken, signKey);
    }

    /**
     * 调用钉钉机器人接口
     *
     * @param url         接口URL
     * @param message     钉钉机器人消息
     * @param accessToken accessToken
     * @param signKey     signKey
     */
    public static ResponseEntity<String> callDingRobot(String url, DingRobotMessage message, String accessToken, String signKey) {
        try {
            HttpEntity<DingRobotMessage> entity = new HttpEntity<>(message, HEADERS);
            long timeStamp = System.currentTimeMillis();
            String sign = sign(timeStamp, signKey);
            return REST_TEMPLATE.postForEntity(url + "?access_token=" + accessToken + "&timestamp=" + timeStamp + "&sign=" + sign, entity, String.class);
        } catch (RestClientResponseException e) {
            log.warn("钉钉消息发送调用失败,收到响应:{}", e.getResponseBodyAsString());
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        }
    }


}
