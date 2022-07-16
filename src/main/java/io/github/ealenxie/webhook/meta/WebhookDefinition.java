package io.github.ealenxie.webhook.meta;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/10 18:02
 * 一个webhook 定义信息
 */
@Getter
@Setter
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
    private WebhookWay way = WebhookWay.DING_MESSAGE;
    /**
     * webhook 配置信息
     */
    private Map<String, Object> config;
    /**
     * gitlab 配置信息
     */
    private GitlabConfig gitlab;
}
