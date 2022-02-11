package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("desc_color")
    private Integer descColor;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getDescColor() {
        return descColor;
    }

    public void setDescColor(Integer descColor) {
        this.descColor = descColor;
    }
}