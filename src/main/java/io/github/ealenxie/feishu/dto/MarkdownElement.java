package io.github.ealenxie.feishu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2022/2/15 13:10
 */

public class MarkdownElement implements Element {


    @JsonProperty("tag")
    private String tag = "markdown";
    @JsonProperty("content")
    private String content;
    @JsonProperty("href")
    private Href href;

    public MarkdownElement() {
    }

    public MarkdownElement(String content) {
        this.content = content;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Href getHref() {
        return href;
    }

    public void setHref(Href href) {
        this.href = href;
    }

    public static class Href {
        @JsonProperty("urlVal")
        private UrlVal urlVal;

        public UrlVal getUrlVal() {
            return urlVal;
        }

        public void setUrlVal(UrlVal urlVal) {
            this.urlVal = urlVal;
        }

        public static class UrlVal {
            @JsonProperty("url")
            private String url;
            @JsonProperty("android_url")
            private String androidUrl;
            @JsonProperty("ios_url")
            private String iosUrl;
            @JsonProperty("pc_url")
            private String pcUrl;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAndroidUrl() {
                return androidUrl;
            }

            public void setAndroidUrl(String androidUrl) {
                this.androidUrl = androidUrl;
            }

            public String getIosUrl() {
                return iosUrl;
            }

            public void setIosUrl(String iosUrl) {
                this.iosUrl = iosUrl;
            }

            public String getPcUrl() {
                return pcUrl;
            }

            public void setPcUrl(String pcUrl) {
                this.pcUrl = pcUrl;
            }
        }
    }
}
