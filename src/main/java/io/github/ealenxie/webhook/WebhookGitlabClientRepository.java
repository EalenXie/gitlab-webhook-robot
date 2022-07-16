package io.github.ealenxie.webhook;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.client.gitlab.GitlabClient;
import io.github.ealenxie.config.WebhookProperties;
import io.github.ealenxie.webhook.meta.GitlabConfig;
import io.github.ealenxie.webhook.meta.WebhookDefinition;
import org.springframework.web.client.RestOperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/15 22:54
 */
public class WebhookGitlabClientRepository implements GitlabClientRepository {
    private final Map<String, GitlabClient> clients = new HashMap<>();
    private final ObjectMapper objectMapper;
    private final RestOperations restOperations;

    public WebhookGitlabClientRepository(WebhookProperties webhookProperties, RestOperations restOperations) {
        this(webhookProperties, new ObjectMapper(), restOperations);
    }

    public WebhookGitlabClientRepository(WebhookProperties webhookProperties, ObjectMapper objectMapper, RestOperations restOperations) {
        this.objectMapper = objectMapper;
        this.restOperations = restOperations;
        List<WebhookDefinition> webhooks = webhookProperties.getWebhooks();
        for (WebhookDefinition webhook : webhooks) {
            save(webhook);
        }
    }


    public GitlabClient findByWebhook(String id) {
        return clients.get(id);
    }

    public void save(WebhookDefinition webhook) {
        GitlabConfig gitlab = webhook.getGitlab();
        if (gitlab != null) {
            clients.put(webhook.getId(), new GitlabClient(gitlab.getHost(), gitlab.getPrivateToken(), objectMapper, restOperations));
        }
    }

    @Override
    public void delete(String id) {
        clients.remove(id);
    }

}



