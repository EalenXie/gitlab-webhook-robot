package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class MarkdownMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "markdown";

    public String getMsgType() {
        return msgType;
    }

    @Getter
    @Setter
    private Markdown markdown;

    public MarkdownMessage(Markdown markdown) {
        this.markdown = markdown;
    }

    @Data
    @NoArgsConstructor
    public static class Markdown {
        private String content;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("mentioned_list")
        private String[] mentionedList;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("mentioned_mobile_list")
        private String[] mentionedMobileList;
    }
}
