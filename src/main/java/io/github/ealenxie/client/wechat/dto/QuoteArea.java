package io.github.ealenxie.client.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteArea {
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("pagepath")
    private String pagePath;
    @JsonProperty("title")
    private String title;
    @JsonProperty("quote_text")
    private String quoteText;
}