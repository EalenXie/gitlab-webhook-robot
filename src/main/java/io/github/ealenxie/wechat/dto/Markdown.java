package io.github.ealenxie.wechat.dto;

/**
 * Created by EalenXie on 2022/2/11 12:35
 */
public class Markdown {

    private String content;

    public Markdown() {
    }

    public Markdown(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
