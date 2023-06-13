package io.github.ealenxie.webhook.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeiShuConfig implements WebhookConfig {
    private String url;
    private String signKey;

}
