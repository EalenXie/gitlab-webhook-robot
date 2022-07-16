package io.github.ealenxie.webhook;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.ealenxie.webhook.handler.WebhookEventHandlerFactory;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/16 13:23
 */
public interface WebhookEventExecutor<T> extends WebhookEventHandlerFactory<T> {

    /**
     * 执行webhook 事件
     *
     * @param webhook webhook
     * @param event   事件
     * @param body    请求体
     * @return 执行结果
     */
    T execute(WebhookDefinition webhook, String event, JsonNode body);
}
