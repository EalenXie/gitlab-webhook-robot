package io.github.ealenxie.webhook.handler;

import io.github.ealenxie.webhook.meta.WebhookWay;

/**
 * Created by EalenXie on 2022/7/10 14:53
 * webhook 工厂
 */
public interface WebhookEventHandlerFactory<R> {

    WebhookEventHandler<R> getByWebhookWay(WebhookWay webhookWay);


}
