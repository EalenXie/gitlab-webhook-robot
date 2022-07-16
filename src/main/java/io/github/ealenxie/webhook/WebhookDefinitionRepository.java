package io.github.ealenxie.webhook;

import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.List;

/**
 * Created by EalenXie on 2022/7/15 22:20
 */
public interface WebhookDefinitionRepository {


    List<WebhookDefinition> getWebhooks();

    WebhookDefinition getWebhook(String id);

}
