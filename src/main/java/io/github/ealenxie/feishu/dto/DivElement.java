package io.github.ealenxie.feishu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2022/2/15 12:54
 */

public class DivElement implements Element {

    @JsonProperty("tag")
    private String tag = "div";
    @JsonProperty("text")
    private Text text;

    public DivElement() {
    }

    public DivElement(Text text) {
        this.text = text;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public static class Text {
        @JsonProperty("content")
        private String content;
        @JsonProperty("tag")
        private String tag;

        public Text() {
        }

        public Text(String content, String tag) {
            this.content = content;
            this.tag = tag;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
