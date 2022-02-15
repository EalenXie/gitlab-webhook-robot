package io.github.ealenxie.gitlab.webhook.sender;

import io.github.ealenxie.feishu.FeiShuClient;
import io.github.ealenxie.feishu.dto.Interactive;
import io.github.ealenxie.feishu.dto.MarkdownElement;
import io.github.ealenxie.feishu.message.InteractiveMessage;
import io.github.ealenxie.gitlab.webhook.conf.WebHookConfig;
import io.github.ealenxie.gitlab.webhook.dto.MarkDownMsg;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by EalenXie on 2022/2/14 17:36
 */
@Component
@ConditionalOnProperty(prefix = WebHookConfig.PREFIX, value = "way", havingValue = "feishu")
public class FeiShuMessageSender implements MessageSender<MarkDownMsg, String> {

    private final FeiShuClient feiShuClient;

    private final String url;

    private final String signKey;


    public FeiShuMessageSender(WebHookConfig webHookConfig, RestTemplate httpClientRestTemplate) {
        this.feiShuClient = new FeiShuClient(httpClientRestTemplate);
        this.url = webHookConfig.getFeishu().getUrl();
        this.signKey = webHookConfig.getFeishu().getSignKey();
    }


    @Override
    public ResponseEntity<String> sendMessage(MarkDownMsg markDownMsg) {
        Interactive interactive = new Interactive();
        interactive.setConfig(new Interactive.Config(true, true));
        interactive.setHeader(new Interactive.Header(new Interactive.Header.Title(markDownMsg.getTitle(), "plain_text")));
        MarkdownElement markdownElement = new MarkdownElement(markDownMsg.getMarkdown());
        interactive.setElements(Collections.singletonList(markdownElement));
        InteractiveMessage interactiveMessage = new InteractiveMessage(interactive);
        return feiShuClient.sendMessage(url, interactiveMessage, signKey);
    }
}
