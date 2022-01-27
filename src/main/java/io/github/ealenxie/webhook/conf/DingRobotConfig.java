package io.github.ealenxie.webhook.conf;

import io.github.ealenxie.webhook.dto.Emoji;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static io.github.ealenxie.webhook.conf.DingRobotConfig.PREFIX;

/**
 * Created by EalenXie on 2021/12/27 14:52
 * 钉钉机器人的配置
 */
@Configuration
@ConfigurationProperties(prefix = PREFIX)
public class DingRobotConfig {

    public static final String PREFIX = "ding.robot";


    private String url = "https://oapi.dingtalk.com/robot/send";

    private String accessToken;

    private String signKey;

    /**
     * 消息是否使用emoji
     */
    private Boolean enableEmoji;

    public boolean isEnableEmoji() {
        return enableEmoji;
    }

    public void setEnableEmoji(boolean enableEmoji) {
        this.enableEmoji = enableEmoji;
        Emoji.enableEmoji(enableEmoji);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }
}
