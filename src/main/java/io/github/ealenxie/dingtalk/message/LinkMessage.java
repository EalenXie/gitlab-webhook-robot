package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.Link;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class LinkMessage extends DingRobotMessage {


    @JsonProperty("msgtype")
    private String msgType = "link";

    public String getMsgType() {
        return msgType;
    }

    public LinkMessage() {
    }

    public LinkMessage(Link link) {
        this.link = link;
    }

    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
