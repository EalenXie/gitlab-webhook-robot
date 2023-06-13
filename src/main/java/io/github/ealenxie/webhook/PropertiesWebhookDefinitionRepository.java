package io.github.ealenxie.webhook;

import io.github.ealenxie.config.WebhookProperties;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/15 22:22
 */
public class PropertiesWebhookDefinitionRepository implements WebhookDefinitionRepository {
    private final WebhookProperties properties;

    private final Map<String, WebhookDefinition> webhookDefinitions = new HashMap<>();

    public PropertiesWebhookDefinitionRepository(WebhookProperties properties) {
        this.properties = properties;
        List<WebhookDefinition> webhooks = properties.getWebhooks();
        for (WebhookDefinition webhook : webhooks) {
            webhookDefinitions.put(webhook.getId(), webhook);
        }
    }

    @Override
    public List<WebhookDefinition> getWebhooks() {
        return properties.getWebhooks();
    }

    @Override
    public WebhookDefinition getWebhook(String id) {
        return webhookDefinitions.get(id);
    }
}
