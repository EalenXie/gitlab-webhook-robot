package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.Text;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class TextMessage extends DingRobotMessage {

    @JsonProperty("msgtype")
    private String msgType = "text";
    @Setter
    @Getter
    private Text text;

    public TextMessage(Text text) {
        this.text = text;
    }

    public String getMsgType() {
        return msgType;
    }


}
