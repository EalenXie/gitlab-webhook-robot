package io.github.ealenxie.webhook.handler.sender;

import io.github.ealenxie.webhook.handler.sender.message.EventMessage;
import io.github.ealenxie.webhook.handler.sender.message.EventMessageGenerator;

/**
 * Created by EalenXie on 2022/7/10 17:47
 * 飞书机器人
 */
public class FeishuSendMessageHandler implements WebhookEventSendMessageHandler<String> {

    public final EventMessageGenerator eventMessageGenerator;


    public FeishuSendMessageHandler(EventMessageGenerator eventMessageGenerator) {
        this.eventMessageGenerator = eventMessageGenerator;
    }

    @Override
    public String sendMessage(EventMessage message) {
        // TODO
        return null;
    }

    @Override
    public EventMessageGenerator getEventMessageGenerator() {
        return eventMessageGenerator;
    }
}
