package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Created by EalenXie on 2022/2/11 13:08
 */
@NoArgsConstructor
public class ImageMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "image";

    @Getter
    @Setter
    private Image image;

    public String getMsgType() {
        return msgType;
    }

    public ImageMessage(Image image) {
        this.image = image;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {
        @JsonProperty("base64")
        private String base64;
        @JsonProperty("md5")
        private String md5;
    }
}
