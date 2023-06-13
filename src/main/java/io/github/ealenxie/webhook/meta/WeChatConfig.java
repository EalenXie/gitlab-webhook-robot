package io.github.ealenxie.webhook.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeChatConfig implements WebhookConfig {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}