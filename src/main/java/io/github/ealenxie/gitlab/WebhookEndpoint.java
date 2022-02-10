package io.github.ealenxie.gitlab;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.ealenxie.gitlab.webhook.DingRobotWebHookHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/1 9:17
 */
@RestController
public class WebhookEndpoint {

    private static final String EVENT_HEADER = "X-Gitlab-Event";

    @Resource
    private DingRobotWebHookHandler dingRobotWebHookHandler;

    @PostMapping("/actuator/gitlab/webhook")
    public ResponseEntity<String> pipeline(@RequestBody JsonNode requestBody, @RequestHeader(EVENT_HEADER) String event) {
        return dingRobotWebHookHandler.handle(requestBody, event);
    }
}
