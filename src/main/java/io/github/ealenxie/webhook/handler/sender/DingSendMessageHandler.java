package io.github.ealenxie.webhook.handler.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.client.dingtalk.DingRobotClient;
import io.github.ealenxie.client.dingtalk.dto.Markdown;
import io.github.ealenxie.client.dingtalk.message.DingRobotAt;
import io.github.ealenxie.client.dingtalk.message.MarkdownMessage;
import io.github.ealenxie.client.gitlab.GitlabClient;
import io.github.ealenxie.webhook.GitlabClientRepository;
import io.github.ealenxie.webhook.handler.sender.message.EventMessage;
import io.github.ealenxie.webhook.handler.sender.message.EventMessageGenerator;
import io.github.ealenxie.webhook.meta.DingDingConfig;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/10 17:47
 * 钉钉机器人
 */
public class DingSendMessageHandler implements WebhookEventSendMessageHandler<Object> {
    public final EventMessageGenerator eventMessageGenerator;

    private final GitlabClientRepository gitlabClientRepository;

    private final DingRobotClient dingRobotClient;

    private final ObjectMapper objectMapper;


    public DingSendMessageHandler(DingRobotClient dingRobotClient,
                                  ObjectMapper objectMapper,
                                  GitlabClientRepository gitlabClientRepository,
                                  EventMessageGenerator eventMessageGenerator) {
        this.dingRobotClient = dingRobotClient;
        this.objectMapper = objectMapper;
        this.eventMessageGenerator = eventMessageGenerator;
        this.gitlabClientRepository = gitlabClientRepository;
    }

    @Override
    public Object sendMessage(WebhookDefinition webhook, EventMessage message) {
        Map<String, Object> config = webhook.getConfig();
        DingDingConfig dingDingConfig = objectMapper.convertValue(config, DingDingConfig.class);
        MarkdownMessage actionCardMessage = new MarkdownMessage();
        StringBuilder sb = new StringBuilder();
        if (!message.notifies().isEmpty()) {
            List<String> atMobiles = new ArrayList<>();
            GitlabClient gitlabClient = gitlabClientRepository.findByWebhook(webhook.getId());
            if (gitlabClient != null) {
                List<String> notifier = message.notifies();
                for (String s : notifier) {
                    String skype = gitlabClient.getUserSkype(Long.parseLong(s));
                    if (skype != null) {
                        sb.append("@").append(skype);
                        atMobiles.add(skype);
                    }
                }
                if (sb.length() > 0) {
                    sb.append("\n\n");
                }
            }
            DingRobotAt at = new DingRobotAt();
            at.setAtMobiles(atMobiles);
            actionCardMessage.setAt(at);
        }
        sb.append(message.message());
        Markdown markdown = new Markdown(message.title(), sb.toString());
        actionCardMessage.setMarkdown(markdown);
        return dingRobotClient.sendMessage(actionCardMessage,
                dingDingConfig.getAccessToken(), dingDingConfig.getSignKey()).getBody();
    }

    @Override
    public EventMessageGenerator getEventMessageGenerator() {
        return eventMessageGenerator;
    }
}
