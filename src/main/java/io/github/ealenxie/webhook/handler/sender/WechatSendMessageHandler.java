package io.github.ealenxie.webhook.handler.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.client.gitlab.GitlabClient;
import io.github.ealenxie.client.wechat.WeChatClient;
import io.github.ealenxie.client.wechat.dto.Markdown;
import io.github.ealenxie.client.wechat.message.MarkdownMessage;
import io.github.ealenxie.webhook.GitlabClientRepository;
import io.github.ealenxie.webhook.handler.sender.message.EventMessage;
import io.github.ealenxie.webhook.handler.sender.message.EventMessageGenerator;
import io.github.ealenxie.webhook.meta.WeChatConfig;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/10 17:47
 * 钉钉机器人
 */
public class WechatSendMessageHandler implements WebhookEventSendMessageHandler<Object> {

    private final EventMessageGenerator eventMessageGenerator;

    private final WeChatClient weChatClient;
    private final ObjectMapper objectMapper;
    private final GitlabClientRepository gitlabClientRepository;

    public WechatSendMessageHandler(WeChatClient weChatClient,
                                    ObjectMapper objectMapper, EventMessageGenerator eventMessageGenerator,
                                    GitlabClientRepository gitlabClientRepository

    ) {
        this.weChatClient = weChatClient;
        this.objectMapper = objectMapper;
        this.eventMessageGenerator = eventMessageGenerator;
        this.gitlabClientRepository = gitlabClientRepository;
    }

    @Override
    public Object sendMessage(WebhookDefinition webhook, EventMessage message) {
        Map<String, Object> config = webhook.getConfig();
        WeChatConfig weChatConfig = objectMapper.convertValue(config, WeChatConfig.class);
        Markdown markdown = new Markdown();
        StringBuilder sb = new StringBuilder();
        if (!message.notifies().isEmpty()) {
            List<String> atMobiles = new ArrayList<>();
            GitlabClient gitlabClient = gitlabClientRepository.findByWebhook(webhook.getId());
            if (gitlabClient != null) {
                List<String> notifier = message.notifies();
                for (String s : notifier) {
                    String skype = gitlabClient.getUserSkype(Long.parseLong(s));
                    if (skype != null) {
                        atMobiles.add(skype);
                    }
                }
            }
            markdown.setMentionedMobileList(atMobiles.toArray(new String[0]));
        }
        sb.append(message.message());
        markdown.setContent(sb.toString());
        MarkdownMessage actionCardMessage = new MarkdownMessage(markdown);
        return weChatClient.sendMessage(actionCardMessage, weChatConfig.getKey()).getBody();
    }

    @Override
    public EventMessageGenerator getEventMessageGenerator() {
        return eventMessageGenerator;
    }
}
