package io.github.ealenxie.webhook.meta;


import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/10 18:02
 * 一个webhook 定义信息
 */
@Getter
@Setter
@Validated
public class WebhookDefinition {

    /**
     * webhookId
     */
    @NotBlank(message = "webhook id is required")
    private String id;
    /**
     * webhook 处理机制 默认钉钉机器人
     */
    @NotNull(message = "webhook way is required")
    private WebhookWay way = WebhookWay.ding_message;
    /**
     * webhook 配置信息
     * 键值对请查看 {@link io.github.ealenxie.webhook.meta.WebhookConfig}
     */
    @NotEmpty(message = "webhook config is required")
    private Map<String, String> config;
    /**
     * gitlab 配置信息
     * 配置了gitlab后, 可在此项目中使用部分gitlab rest api
     */
    private GitlabConfig gitlab;
}
