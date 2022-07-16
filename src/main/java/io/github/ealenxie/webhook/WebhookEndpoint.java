package io.github.ealenxie.webhook;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.ealenxie.webhook.WebhookDefinitionRepository;
import io.github.ealenxie.webhook.DefaultWebhookEventExecutor;
import io.github.ealenxie.webhook.meta.WebhookDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/1 9:17
 */
@RestController
public class WebhookEndpoint {
    private static final String EVENT_HEADER = "X-Gitlab-Event";
    public static final String PIPELINE_ENDPOINT_URL = "/actuator/gitlab/webhook";
    @Resource
    private DefaultWebhookEventExecutor webhookEventExecutor;
    @Resource
    private WebhookDefinitionRepository webhookDefinitionRepository;

    @PostMapping(PIPELINE_ENDPOINT_URL + "/{id}")
    public ResponseEntity<Object> webhook(@PathVariable String id, @RequestHeader(EVENT_HEADER) String event, @RequestBody JsonNode requestBody) {
        WebhookDefinition webhook = webhookDefinitionRepository.getWebhook(id);
        if (webhook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(webhookEventExecutor.execute(webhook, event, requestBody));
    }
}
