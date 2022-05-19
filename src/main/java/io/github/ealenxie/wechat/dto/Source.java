package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source {
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("desc_color")
    private Integer descColor;
}