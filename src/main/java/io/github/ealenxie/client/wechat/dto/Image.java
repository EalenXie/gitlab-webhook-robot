package io.github.ealenxie.client.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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