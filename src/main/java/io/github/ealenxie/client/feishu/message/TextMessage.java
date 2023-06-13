package io.github.ealenxie.client.feishu.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/2/14 17:16
 */

public class TextMessage implements FeiShuMessage {
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sign")
    private String sign;
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestamp")
    private String timestamp;
    @Getter
    @JsonProperty("msg_type")
    private String msgType = "text";
    @Getter
    @Setter
    @JsonProperty("content")
    private Content content;

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class Content {
        @JsonProperty("text")
        private String text;
    }
}
