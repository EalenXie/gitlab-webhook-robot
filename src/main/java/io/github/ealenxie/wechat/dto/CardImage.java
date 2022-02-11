package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2022/2/11 15:51
 */
public class CardImage {


    @JsonProperty("url")
    private String url;
    @JsonProperty("aspect_ratio")
    private Double aspectRatio;

    public CardImage() {
    }

    public CardImage(String url, Double aspectRatio) {
        this.url = url;
        this.aspectRatio = aspectRatio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }


}
