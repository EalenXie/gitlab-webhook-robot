package io.github.ealenxie.gitlab.webhook.sender;

import io.github.ealenxie.gitlab.webhook.conf.WebHookConfig;
import io.github.ealenxie.gitlab.webhook.dto.MarkDownMsg;
import io.github.ealenxie.wechat.WeChatClient;
import io.github.ealenxie.wechat.dto.Markdown;
import io.github.ealenxie.wechat.message.MarkdownMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by EalenXie on 2022/2/11 16:42
 */
@Component
@ConditionalOnProperty(prefix = WebHookConfig.PREFIX, value = "way", havingValue = "wechat")
public class WeChatMessageSender implements MessageSender<MarkDownMsg, String> {

    private final WeChatClient weChatClient;

    private final String key;


    public WeChatMessageSender(WebHookConfig webHookConfig, RestTemplate httpClientRestTemplate) {
        this.weChatClient = new WeChatClient(httpClientRestTemplate);
        this.key = webHookConfig.getWechat().getKey();
    }


    @Override
    public ResponseEntity<String> sendMessage(MarkDownMsg markDownMsg) {
        Markdown markdown = new Markdown(markDownMsg.getMarkdown());
        MarkdownMessage actionCardMessage = new MarkdownMessage(markdown);
        return weChatClient.sendMessage(actionCardMessage, key);
    }

}
