package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class File {
    @JsonProperty("media_id")
    private String mediaId;
}