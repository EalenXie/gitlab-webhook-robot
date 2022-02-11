package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteArea {
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("pagepath")
    private String pagepath;
    @JsonProperty("title")
    private String title;
    @JsonProperty("quote_text")
    private String quoteText;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }
}