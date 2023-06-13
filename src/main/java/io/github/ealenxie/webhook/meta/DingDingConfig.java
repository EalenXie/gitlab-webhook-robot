package io.github.ealenxie.webhook.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/7/10 21:58
 */
@Getter
@Setter
public class DingDingConfig implements WebhookConfig {

    private String url = "https://oapi.dingtalk.com/robot/send";
    private String accessToken;
    private String signKey;
}
