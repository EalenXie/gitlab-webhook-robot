package io.github.ealenxie.feishu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/2/15 12:43
 */
@Getter
@Setter
public class Interactive {
    @JsonProperty("config")
    private Config config;
    @JsonProperty("elements")
    private List<Element> elements;
    @JsonProperty("header")
    private Header header;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Config {
        @JsonProperty("wide_screen_mode")
        private Boolean wideScreenMode;
        @JsonProperty("enable_forward")
        private Boolean enableForward;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Header {
        @JsonProperty("title")
        private Title title;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        public static class Title {
            @JsonProperty("content")
            private String content;
            @JsonProperty("tag")
            private String tag;
        }
    }
}
