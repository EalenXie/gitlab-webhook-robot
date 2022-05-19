package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.wechat.dto.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}
