package io.github.ealenxie.gitlab.webhook.sender;

import io.github.ealenxie.gitlab.GitlabClient;
import io.github.ealenxie.gitlab.GitlabUserFactory;
import io.github.ealenxie.gitlab.config.GitlabConfig;
import io.github.ealenxie.gitlab.webhook.conf.WebHookConfig;
import io.github.ealenxie.gitlab.webhook.dto.MarkDownMsg;
import io.github.ealenxie.wechat.WeChatClient;
import io.github.ealenxie.wechat.dto.Markdown;
import io.github.ealenxie.wechat.message.MarkdownMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EalenXie on 2022/2/11 16:42
 */
@Component
@ConditionalOnProperty(prefix = WebHookConfig.PREFIX, value = "way", havingValue = "wechat")
public class WeChatMessageSender implements MessageSender<MarkDownMsg, String> {

    private final WeChatClient weChatClient;

    private final String key;
    private final GitlabUserFactory userFactory;

    public WeChatMessageSender(WebHookConfig webHookConfig, RestTemplate httpClientRestTemplate, GitlabConfig gitlabConfig) {
        this.weChatClient = new WeChatClient(httpClientRestTemplate);
        this.key = webHookConfig.getWechat().getKey();
        if (gitlabConfig.getHost() != null && gitlabConfig.getPrivateToken() != null) {
            this.userFactory = new GitlabUserFactory(new GitlabClient(gitlabConfig.getHost(), gitlabConfig.getPrivateToken()));
        } else {
            this.userFactory = null;
        }
    }


    @Override
    public ResponseEntity<String> sendMessage(MarkDownMsg markDownMsg) {
        Markdown markdown = new Markdown();
        StringBuilder sb = new StringBuilder();
        if (!markDownMsg.notifier().isEmpty()) {
            List<String> atMobiles = new ArrayList<>();
            if (userFactory != null) {
                List<String> notifier = markDownMsg.notifier();
                for (String s : notifier) {
                    String skype = userFactory.getUserSkype(Long.parseLong(s));
                    if (skype != null) {
                        atMobiles.add(skype);
                    }
                }
            }
            markdown.setMentionedMobileList(atMobiles.toArray(new String[0]));
        }
        sb.append(markDownMsg.getMarkdown());
        markdown.setContent(sb.toString());
        MarkdownMessage actionCardMessage = new MarkdownMessage(markdown);
        return weChatClient.sendMessage(actionCardMessage, key);
    }

}
