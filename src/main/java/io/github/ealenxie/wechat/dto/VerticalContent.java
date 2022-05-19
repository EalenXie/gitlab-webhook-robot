package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerticalContent {
    @JsonProperty("title")
    private String title;
    @JsonProperty("desc")
    private String desc;
}