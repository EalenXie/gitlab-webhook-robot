package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class MarkdownMessage extends DingRobotMessage {


    @JsonProperty("msgtype")
    private String msgType = "markdown";
    @Setter
    @Getter
    private Markdown markdown;

    public String getMsgType() {
        return msgType;
    }

    public MarkdownMessage(Markdown markdown) {
        this.markdown = markdown;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Markdown {
        private String title;
        private String text;
    }
}
