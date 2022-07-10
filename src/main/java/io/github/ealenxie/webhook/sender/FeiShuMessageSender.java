package io.github.ealenxie.webhook.sender;

import io.github.ealenxie.client.feishu.FeiShuClient;
import io.github.ealenxie.client.feishu.dto.*;
import io.github.ealenxie.client.feishu.message.InteractiveMessage;
import io.github.ealenxie.webhook.conf.WebHookConfig;
import io.github.ealenxie.webhook.dto.MarkDownMsg;
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
        interactive.setConfig(new Config(true, true));
        interactive.setHeader(new Header(new Title(markDownMsg.getTitle(), "plain_text")));
        MarkdownElement markdownElement = new MarkdownElement(markDownMsg.getMarkdown());
        interactive.setElements(Collections.singletonList(markdownElement));
        InteractiveMessage interactiveMessage = new InteractiveMessage(interactive);
        return feiShuClient.sendMessage(url, interactiveMessage, signKey);
    }
}
