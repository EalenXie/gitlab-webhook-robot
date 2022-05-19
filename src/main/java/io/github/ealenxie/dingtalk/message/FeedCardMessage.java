package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.FeedCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class FeedCardMessage extends DingRobotMessage {

    @JsonProperty("msgtype")
    private String msgType = "feedCard";

    public String getMsgType() {
        return msgType;
    }

    @Getter
    @Setter
    private FeedCard feedCard;

    public FeedCardMessage(FeedCard feedCard) {
        this.feedCard = feedCard;
    }

}
