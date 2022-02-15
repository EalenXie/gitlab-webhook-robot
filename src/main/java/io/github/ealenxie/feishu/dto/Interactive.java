package io.github.ealenxie.feishu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by EalenXie on 2022/2/15 12:43
 */

public class Interactive {

    @JsonProperty("config")
    private Config config;
    @JsonProperty("elements")
    private List<Element> elements;
    @JsonProperty("header")
    private Header header;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public static class Config {
        @JsonProperty("wide_screen_mode")
        private Boolean wideScreenMode;
        @JsonProperty("enable_forward")
        private Boolean enableForward;

        public Config(Boolean wideScreenMode, Boolean enableForward) {
            this.wideScreenMode = wideScreenMode;
            this.enableForward = enableForward;
        }

        public Config() {
        }

        public Boolean getWideScreenMode() {
            return wideScreenMode;
        }

        public void setWideScreenMode(Boolean wideScreenMode) {
            this.wideScreenMode = wideScreenMode;
        }

        public Boolean getEnableForward() {
            return enableForward;
        }

        public void setEnableForward(Boolean enableForward) {
            this.enableForward = enableForward;
        }
    }

    public static class Header {
        @JsonProperty("title")
        private Title title;

        public Header(Title title) {
            this.title = title;
        }

        public Header() {
        }

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public static class Title {
            @JsonProperty("content")
            private String content;
            @JsonProperty("tag")
            private String tag;

            public Title() {
            }

            public Title(String content, String tag) {
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
}
