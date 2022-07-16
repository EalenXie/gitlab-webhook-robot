package io.github.ealenxie.config;

import io.github.ealenxie.webhook.WebhookEndpoint;
import io.github.ealenxie.webhook.WebhookDefinitionRepository;
import io.github.ealenxie.webhook.meta.WebhookDefinition;
import io.github.ealenxie.webhook.tool.SpringEnvHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by EalenXie on 2022/2/18 9:38
 */
@Slf4j
@Component
public class WebhookCommandLineRunner implements CommandLineRunner {


    @Resource
    private Environment environment;

    @Resource
    private WebhookDefinitionRepository webhookDefinitionRepository;

    @Override
    public void run(String... args) {
        String ip = SpringEnvHelper.getLocalhostIp();
        int port = SpringEnvHelper.getPort();
        String contextPath = environment.getProperty("server.servlet.context-path");
        String contentPath;
        if (contextPath != null) {
            contentPath = String.format("%s://%s:%s%s", "http", ip, port, contextPath);
        } else {
            contentPath = String.format("%s://%s:%s", "http", ip, port);
        }
        List<WebhookDefinition> webhooks = webhookDefinitionRepository.getWebhooks();
        if (!webhooks.isEmpty()) {
            StringBuilder sb = new StringBuilder("Webhooks are successfully configured.\n");
            sb.append("The following webhooks are available,Please fill in these address in your Gitlab Webhook: \n");
            for (WebhookDefinition webhook : webhooks) {
                sb.append(String.format("Webhook address: %s%s/%s %n", contentPath, WebhookEndpoint.PIPELINE_ENDPOINT_URL, webhook.getId()));
            }
            log.info(sb.toString());
        }


    }
}
