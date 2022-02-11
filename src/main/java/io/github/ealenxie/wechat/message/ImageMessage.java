package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.wechat.dto.Image;

/**
 * Created by EalenXie on 2022/2/11 13:08
 */
public class ImageMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "image";

    private Image image;

    public String getMsgType() {
        return msgType;
    }

    public ImageMessage() {
    }

    public ImageMessage(Image image) {
        this.image = image;
    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
