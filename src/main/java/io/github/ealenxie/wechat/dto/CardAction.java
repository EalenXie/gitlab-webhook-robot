package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardAction {
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("pagepath")
    private String pagepath;

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
}