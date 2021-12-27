package io.github.ealenxie.dingtalk.message;

import io.github.ealenxie.dingtalk.dto.Markdown;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class MarkdownMessage extends DingRobotMessage {

    private static final String MSG_TYPE = "markdown";

    @Override
    public String getMsgType() {
        return MSG_TYPE;
    }

    private Markdown markdown;

    public Markdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Markdown markdown) {
        this.markdown = markdown;
    }
}
