package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/16 22:59
 */
public abstract class WebhookMessage implements EmojiSupport, EventMessage {

    private final WebhookDefinition webhook;
    private boolean enableEmoji;

    public boolean isEnableEmoji() {
        return enableEmoji;
    }

    public void setEnableEmoji(boolean enableEmoji) {
        this.enableEmoji = enableEmoji;
    }

    public WebhookDefinition getWebhook() {
        return webhook;
    }


    protected WebhookMessage(WebhookDefinition webhook) {
        this.webhook = webhook;
    }

    public WebhookDefinition webhook() {
        return webhook;
    }
}
