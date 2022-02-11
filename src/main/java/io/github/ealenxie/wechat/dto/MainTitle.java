package io.github.ealenxie.wechat.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MainTitle {
    @JsonProperty("title")
    private String title;
    @JsonProperty("desc")
    private String desc;

    public MainTitle() {
    }

    public MainTitle(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}