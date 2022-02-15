package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Created by EalenXie on 2022/2/11 13:18
 */

public class FileMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "file";

    @Getter
    @Setter
    private File file;

    @Override
    public String getMsgType() {
        return msgType;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class File {
        @JsonProperty("media_id")
        private String mediaId;
    }
}
