package io.github.ealenxie.webhook.handler;

import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/10 14:53
 * webhook 处理器工厂
 */
public interface WebhookEventHandlerFactory<R> {

    /**
     * 根据Webhook 获取事件处理器
     *
     * @param webhook 请求的webhook
     */
    GitlabWebhookEventHandler<R> getByWebhook(WebhookDefinition webhook);


}
