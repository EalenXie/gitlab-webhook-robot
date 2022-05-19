package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.Link;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class LinkMessage extends DingRobotMessage {

    @JsonProperty("msgtype")
    private String msgType = "link";
    @Getter
    @Setter
    private Link link;

    public String getMsgType() {
        return msgType;
    }

    public LinkMessage(Link link) {
        this.link = link;
    }


}
