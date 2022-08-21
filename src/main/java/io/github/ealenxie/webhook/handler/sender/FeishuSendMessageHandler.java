package io.github.ealenxie.webhook.handler.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.client.feishu.FeiShuClient;
import io.github.ealenxie.client.feishu.dto.*;
import io.github.ealenxie.client.feishu.message.InteractiveMessage;
import io.github.ealenxie.webhook.handler.sender.message.EventMessage;
import io.github.ealenxie.webhook.meta.FeiShuConfig;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.Collections;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/10 17:47
 * 飞书机器人
 */
public class FeishuSendMessageHandler implements WebhookEventSendMessageHandler<Object> {

    public final EventMessageGenerator eventMessageGenerator;

    private final FeiShuClient feiShuClient;
    private final ObjectMapper objectMapper;

    public FeishuSendMessageHandler(FeiShuClient feiShuClient,
                                    ObjectMapper objectMapper,
                                    EventMessageGenerator eventMessageGenerator) {
        this.feiShuClient = feiShuClient;
        this.objectMapper = objectMapper;
        this.eventMessageGenerator = eventMessageGenerator;

    }

    @Override
    public Object sendMessage(EventMessage message) {
        WebhookDefinition webhook = message.webhook();
        Map<String, String> config = webhook.getConfig();
        FeiShuConfig feiShuConfig = objectMapper.convertValue(config, FeiShuConfig.class);
        Interactive interactive = new Interactive();
        interactive.setConfig(new Config(true, true));
        interactive.setHeader(new Header(new Title(message.title(), "plain_text")));
        MarkdownElement markdownElement = new MarkdownElement(message.message());
        interactive.setElements(Collections.singletonList(markdownElement));
        InteractiveMessage interactiveMessage = new InteractiveMessage(interactive);
        return feiShuClient.sendMessage(feiShuConfig.getUrl(), interactiveMessage, feiShuConfig.getSignKey()).getBody();
    }

    @Override
    public EventMessageGenerator getEventMessageGenerator() {
        return eventMessageGenerator;
    }
}
