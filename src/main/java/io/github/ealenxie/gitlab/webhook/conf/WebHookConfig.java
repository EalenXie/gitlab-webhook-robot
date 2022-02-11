package io.github.ealenxie.gitlab.webhook.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Created by EalenXie on 2022/2/11 16:46
 */
@Configuration
@ConfigurationProperties(prefix = WebHookConfig.PREFIX)
public class WebHookConfig {

    public static final String PREFIX = "webhook";
    /**
     * 通知方式 钉钉机器人(ding) / 企业微信(wechat) 默认为ding
     */
    private String way = "ding";


    private DingRobotConfig ding;

    private WeChatConfig wechat;

    public DingRobotConfig getDing() {
        return ding;
    }

    public void setDing(DingRobotConfig ding) {
        this.ding = ding;
    }

    public WeChatConfig getWechat() {
        return wechat;
    }

    public void setWechat(WeChatConfig wechat) {
        this.wechat = wechat;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public static class DingRobotConfig {
        private String url = "https://oapi.dingtalk.com/robot/send";
        private String accessToken;
        private String signKey;

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

    public static class WeChatConfig {

        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }


}
