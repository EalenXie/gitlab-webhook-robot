package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2022/2/11 13:08
 */
public class Image {


    @JsonProperty("base64")
    private String base64;
    @JsonProperty("md5")
    private String md5;

    public Image() {
    }

    public Image(String base64, String md5) {
        this.base64 = base64;
        this.md5 = md5;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
