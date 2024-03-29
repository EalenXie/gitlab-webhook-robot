package io.github.ealenxie.client.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageTextArea {
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("title")
    private String title;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("image_url")
    private String imageUrl;
}
