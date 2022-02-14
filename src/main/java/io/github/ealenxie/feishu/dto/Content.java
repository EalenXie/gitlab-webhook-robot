package io.github.ealenxie.feishu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2022/2/14 17:13
 */
public class Content {

    @JsonProperty("text")
    private String text;

    public Content() {
    }

    public Content(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
