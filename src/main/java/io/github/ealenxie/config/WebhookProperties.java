package io.github.ealenxie.config;

import io.github.ealenxie.webhook.meta.WebhookDefinition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/15 21:56
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(WebhookProperties.PREFIX)
@Validated
public class WebhookProperties {

    public static final String PREFIX = "gitlab";

    private List<WebhookDefinition> webhooks = new ArrayList<>();

}
