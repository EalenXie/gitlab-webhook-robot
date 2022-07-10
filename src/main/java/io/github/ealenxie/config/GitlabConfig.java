package io.github.ealenxie.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by EalenXie on 2022/2/21 10:36
 */
@Configuration
@ConfigurationProperties(prefix = GitlabConfig.PREFIX)
public class GitlabConfig {
    public static final String PREFIX = "gitlab";

    private String host;
    private String privateToken;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

}
