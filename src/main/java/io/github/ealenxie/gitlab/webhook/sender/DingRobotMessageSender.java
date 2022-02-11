package io.github.ealenxie.gitlab.webhook.sender;

import io.github.ealenxie.dingtalk.DingRobotClient;
import io.github.ealenxie.dingtalk.dto.DingRobotAt;
import io.github.ealenxie.dingtalk.dto.Markdown;
import io.github.ealenxie.dingtalk.message.MarkdownMessage;
import io.github.ealenxie.gitlab.webhook.conf.WebHookConfig;
import io.github.ealenxie.gitlab.webhook.dto.MarkDownMsg;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by EalenXie on 2022/2/11 16:29
 */
@Component
@ConditionalOnProperty(prefix = WebHookConfig.PREFIX, value = "way", havingValue = "ding")
public class DingRobotMessageSender implements MessageSender<MarkDownMsg, String> {

    private final DingRobotClient dingRobotClient;
    private final String accessToken;
    private final String signKey;


    public DingRobotMessageSender(WebHookConfig webHookConfig, RestTemplate httpClientRestTemplate) {
        this.dingRobotClient = new DingRobotClient(httpClientRestTemplate);
        this.accessToken = webHookConfig.getDing().getAccessToken();
        this.signKey = webHookConfig.getDing().getSignKey();
    }


    @Override
    public ResponseEntity<String> sendMessage(MarkDownMsg markDownMsg) {
        Markdown markdown = new Markdown(markDownMsg.getTitle(), markDownMsg.getMarkdown());
        MarkdownMessage actionCardMessage = new MarkdownMessage(markdown);
        if (!markDownMsg.notifier().isEmpty()) {
            DingRobotAt at = new DingRobotAt();
            at.setAtMobiles(markDownMsg.notifier());
            actionCardMessage.setAt(at);
        }
        return dingRobotClient.sendMessage(actionCardMessage, accessToken, signKey);
    }
}
