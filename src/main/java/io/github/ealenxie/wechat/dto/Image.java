package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @JsonProperty("base64")
    private String base64;
    @JsonProperty("md5")
    private String md5;
}