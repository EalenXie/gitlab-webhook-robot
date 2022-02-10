package io.github.ealenxie.gitlab.webhook.dto;

/**
 * Created by EalenXie on 2022/1/20 10:06
 */
public class Source {

    private String format;

    private String url;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
