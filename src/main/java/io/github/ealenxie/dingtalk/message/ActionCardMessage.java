package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.ActionCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class ActionCardMessage extends DingRobotMessage {

    @JsonProperty("msgtype")
    private String msgType = "actionCard";
    @Getter
    @Setter
    private ActionCard actionCard;

    public ActionCardMessage(ActionCard actionCard) {
        this.actionCard = actionCard;
    }

    public String getMsgType() {
        return msgType;
    }


}
