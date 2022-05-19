package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorizontalContent {
    @JsonProperty("keyname")
    private String keyName;
    @JsonProperty("value")
    private String value;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("media_id")
    private String mediaId;
}