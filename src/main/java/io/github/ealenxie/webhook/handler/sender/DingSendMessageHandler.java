package io.github.ealenxie.webhook.handler.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.client.dingtalk.DingRobotClient;
import io.github.ealenxie.client.dingtalk.dto.Markdown;
import io.github.ealenxie.client.dingtalk.message.DingRobotAt;
import io.github.ealenxie.client.dingtalk.message.MarkdownMessage;
import io.github.ealenxie.webhook.GitlabHandler;
import io.github.ealenxie.webhook.GitlabHandlerRepository;
import io.github.ealenxie.webhook.handler.sender.message.EventMessage;
import io.github.ealenxie.webhook.meta.DingDingConfig;
import io.github.ealenxie.webhook.meta.WebhookDefinition;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/10 17:47
 * 钉钉机器人
 */
public class DingSendMessageHandler implements WebhookEventSendMessageHandler<Object> {

    private final GitlabHandlerRepository gitlabHandlerRepository;

    private final DingRobotClient dingRobotClient;

    private final EventMessageGenerator eventMessageGenerator;

    private final ObjectMapper objectMapper;

    public DingSendMessageHandler(DingRobotClient dingRobotClient,
                                  ObjectMapper objectMapper,
                                  GitlabHandlerRepository gitlabHandlerRepository,
                                  EventMessageGenerator eventMessageGenerator) {
        this.dingRobotClient = dingRobotClient;
        this.objectMapper = objectMapper;
        this.gitlabHandlerRepository = gitlabHandlerRepository;
        this.eventMessageGenerator = eventMessageGenerator;
    }


    @Override
    public EventMessageGenerator getEventMessageGenerator() {
        return eventMessageGenerator;
    }

    @Override
    public Object sendMessage(EventMessage message) {
        WebhookDefinition webhook = message.webhook();
        Map<String, Object> config = webhook.getConfig();
        DingDingConfig dingDingConfig = objectMapper.convertValue(config, DingDingConfig.class);
        MarkdownMessage actionCardMessage = new MarkdownMessage();
        StringBuilder sb = new StringBuilder();
        if (!message.notifies().isEmpty()) {
            List<String> atMobiles = new ArrayList<>();
            GitlabHandler gitlabHandler = gitlabHandlerRepository.findByWebhook(webhook.getId());
            if (gitlabHandler != null) {
                List<String> notifier = message.notifies();
                for (String s : notifier) {
                    String skype = gitlabHandler.getUserSkype(Long.parseLong(s));
                    if (!ObjectUtils.isEmpty(skype)) {
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

}
