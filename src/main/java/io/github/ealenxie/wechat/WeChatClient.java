package io.github.ealenxie.wechat;

import io.github.ealenxie.wechat.message.WeChatMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Created by EalenXie on 2022/2/11 10:42
 * https://developer.work.weixin.qq.com/document/path/91770
 */
public class WeChatClient {


    private static final String DEFAULT_API_URL = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send";

    private final RestOperations restOperations;

    private final HttpHeaders jsonHeader;

    public WeChatClient() {
        this(new RestTemplate());
    }

    public WeChatClient(RestOperations restOperations) {
        this.restOperations = restOperations;
        jsonHeader = new HttpHeaders();
        jsonHeader.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * @param message 微信机器人消息
     */
    public ResponseEntity<String> sendMessage(WeChatMessage message, String key) {
        try {
            HttpEntity<WeChatMessage> entity = new HttpEntity<>(message, jsonHeader);
            return restOperations.postForEntity(String.format("%s?key=%s", DEFAULT_API_URL, key), entity, String.class);
        } catch (RestClientResponseException e) {
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        }
    }
}
