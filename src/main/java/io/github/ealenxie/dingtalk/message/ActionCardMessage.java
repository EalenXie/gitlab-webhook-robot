package io.github.ealenxie.dingtalk.message;

import io.github.ealenxie.dingtalk.dto.ActionCard;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class ActionCardMessage extends DingRobotMessage {

    private static final String MSG_TYPE = "actionCard";

    @Override
    public String getMsgType() {
        return MSG_TYPE;
    }

    private ActionCard actionCard;

    public ActionCard getActionCard() {
        return actionCard;
    }

    public void setActionCard(ActionCard actionCard) {
        this.actionCard = actionCard;
    }
}
