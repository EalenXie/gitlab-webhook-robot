package io.github.ealenxie.dingtalk.message;

import io.github.ealenxie.dingtalk.dto.Link;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class LinkMessage extends DingRobotMessage {

    private static final String MSG_TYPE = "link";

    @Override
    public String getMsgType() {
        return MSG_TYPE;
    }

    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
