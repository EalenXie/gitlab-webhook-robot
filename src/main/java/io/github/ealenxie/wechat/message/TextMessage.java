package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.wechat.dto.Text;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class TextMessage implements WeChatMessage {

    @JsonProperty("msgtype")
    private String msgType = "text";
    @Getter
    @Setter
    private Text text;

    public String getMsgType() {
        return msgType;
    }

    public TextMessage(Text text) {
        this.text = text;
    }


}
