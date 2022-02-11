package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.wechat.dto.Markdown;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class MarkdownMessage implements WeChatMessage {


    @JsonProperty("msgtype")
    private String msgType = "markdown";

    public String getMsgType() {
        return msgType;
    }

    private Markdown markdown;

    public MarkdownMessage() {
    }

    public MarkdownMessage(Markdown markdown) {
        this.markdown = markdown;
    }

    public Markdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Markdown markdown) {
        this.markdown = markdown;
    }
}
