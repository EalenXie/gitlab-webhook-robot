package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.Text;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class TextMessage implements WeChatMessage {

    @JsonProperty("msgtype")
    private String msgType = "text";
    private Text text;

    public String getMsgType() {
        return msgType;
    }

    public TextMessage() {
    }

    public TextMessage(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
