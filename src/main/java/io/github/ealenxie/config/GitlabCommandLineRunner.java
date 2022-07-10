package io.github.ealenxie.config;

import io.github.ealenxie.endpoint.WebhookEndpoint;
import io.github.ealenxie.webhook.WebHookWay;
import io.github.ealenxie.webhook.conf.WebHookConfig;
import io.github.ealenxie.webhook.tool.SpringEnvHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2022/2/18 9:38
 */
@Slf4j
@Component
public class GitlabCommandLineRunner implements CommandLineRunner {


    @Resource
    private Environment environment;
    @Resource
    private WebHookConfig webHookConfig;

    @Override
    public void run(String... args) {
        String property = environment.getProperty("gitlab.enable");
        if ("true".equals(property)) {
            log.info("Gitlab config enable, You can call the section's Gitlab API resource by GitlabEndpoint");
        }
        StringBuilder sb = new StringBuilder();
        String ip = SpringEnvHelper.getLocalhostIp();
        int port = SpringEnvHelper.getPort();
        String contextPath = environment.getProperty("server.servlet.context-path");
        String contentPath;
        if (contextPath != null) {
            contentPath = String.format("%s://%s:%s%s", "http", ip, port, contextPath);
        } else {
            contentPath = String.format("%s://%s:%s", "http", ip, port);
        }
        WebHookWay way = webHookConfig.getWay();
        sb.append(String.format("The message sending way is %s", way));
        sb.append(String.format(" , Please fill in this address in your Gitlab Webhook: %s%s%n", contentPath, WebhookEndpoint.PIPELINE_ENDPOINT_URL));
        log.info(sb.toString());
    }
}
