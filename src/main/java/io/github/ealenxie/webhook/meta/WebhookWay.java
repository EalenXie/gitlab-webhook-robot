package io.github.ealenxie.webhook.meta;

/**
 * Created by EalenXie on 2022/7/10 22:05
 */
@SuppressWarnings("all")
public enum WebhookWay {
    /**
     * 发送钉钉机器人消息
     */
    DING_MESSAGE,
    /**
     * 发送企业微信机器人消息
     */
    WECHAT_MESSAGE,
    /**
     * 发送飞书机器人消息
     */
    FEI_SHU_MESSAGE
}
