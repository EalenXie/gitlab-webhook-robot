package io.github.ealenxie.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.client.dingtalk.DingRobotClient;
import io.github.ealenxie.client.feishu.FeiShuClient;
import io.github.ealenxie.client.wechat.WeChatClient;
import io.github.ealenxie.webhook.*;
import io.github.ealenxie.webhook.handler.sender.*;
import io.github.ealenxie.webhook.tool.SpringEnvHelper;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebhookAutoConfig {


    @Bean
    @ConditionalOnMissingBean
    public SpringEnvHelper springEnvHelper() {
        return new SpringEnvHelper();
    }

    @Bean
    @ConditionalOnMissingBean(name = "httpClientRestTemplate")
    public RestTemplate httpClientRestTemplate() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());
        connectionManager.setDefaultMaxPerRoute(20);
        connectionManager.setMaxTotal(50);
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5 * 1000);
        factory.setReadTimeout(10 * 1000);
        factory.setHttpClient(closeableHttpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (int i = 0; i < converters.size(); i++) {
            if (converters.get(i) instanceof StringHttpMessageConverter) {
                StringHttpMessageConverter converter = (StringHttpMessageConverter) converters.get(i);
                converter.setDefaultCharset(StandardCharsets.UTF_8);
                converters.set(i, converter);
                break;
            }
        }
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }


    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public EventMessageGenerator eventMessageGenerator() {
        return new DefaultEventMessageGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public DingRobotClient dingRobotClient(RestTemplate httpClientRestTemplate) {
        return new DingRobotClient(httpClientRestTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public FeiShuClient feiShuClient(RestTemplate httpClientRestTemplate) {
        return new FeiShuClient(httpClientRestTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public WeChatClient weChatClient(RestTemplate httpClientRestTemplate) {
        return new WeChatClient(httpClientRestTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public GitlabHandlerRepository gitlabClientRepository(WebhookProperties webhookProperties, RestTemplate httpClientRestTemplate) {
        return new WebhookGitlabHandlerRepository(webhookProperties, httpClientRestTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public FeishuSendMessageHandler feishuSendMessageHandler(FeiShuClient feiShuClient, ObjectMapper objectMapper, EventMessageGenerator eventMessageGenerator) {
        return new FeishuSendMessageHandler(feiShuClient, objectMapper, eventMessageGenerator);
    }

    @Bean
    @ConditionalOnMissingBean
    public WechatSendMessageHandler wechatSendMessageHandler(WeChatClient weChatClient, ObjectMapper objectMapper, EventMessageGenerator eventMessageGenerator,
                                                             GitlabHandlerRepository gitlabHandlerRepository) {
        return new WechatSendMessageHandler(weChatClient, objectMapper, eventMessageGenerator, gitlabHandlerRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public DingSendMessageHandler dingRobotSendMessageHandler(DingRobotClient dingRobotClient,
                                                              ObjectMapper objectMapper,
                                                              EventMessageGenerator eventMessageGenerator, GitlabHandlerRepository gitlabHandlerRepository) {
        return new DingSendMessageHandler(dingRobotClient, objectMapper, gitlabHandlerRepository, eventMessageGenerator);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebhookDefinitionRepository webhookDefinitionRepository(WebhookProperties webhookProperties) {
        return new PropertiesWebhookDefinitionRepository(webhookProperties);

    }

    @Bean
    @ConditionalOnMissingBean
    public WebhookEventExecutor<Object> webhookEventExecutor(ObjectMapper objectMapper) {
        return new DefaultWebhookEventExecutor(objectMapper);
    }
}
