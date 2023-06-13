package io.github.ealenxie.client.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class File {
    @JsonProperty("media_id")
    private String mediaId;
}