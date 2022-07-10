package io.github.ealenxie.webhook.meta;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/7/10 18:02
 * 一个webhook 定义信息
 */
@Getter
@Setter
public class WebhookDefinition {

    /**
     * webhookId
     */
    private String id;
    /**
     * webhook 处理机制
     */
    private WebhookWay way;
    /**
     * webhook 配置信息
     */
    private WebhookConfig config;


}
