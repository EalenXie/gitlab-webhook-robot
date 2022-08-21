package io.github.ealenxie.webhook.meta;

/**
 * Created by EalenXie on 2022/7/10 22:05
 */
@SuppressWarnings("all")
public enum WebhookWay {
    /**
     * 发送钉钉机器人消息
     */
    ding_message,
    /**
     * 发送企业微信机器人消息
     */
    wechat_message,
    /**
     * 发送飞书机器人消息
     */
    fei_shu_message
}
