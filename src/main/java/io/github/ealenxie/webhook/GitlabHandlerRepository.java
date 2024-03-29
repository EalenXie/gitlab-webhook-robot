package io.github.ealenxie.webhook;


import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/15 22:54
 */
public interface GitlabHandlerRepository {

    GitlabHandler findByWebhook(String id);

    void save(WebhookDefinition webhook);

    void delete(String id);


}



