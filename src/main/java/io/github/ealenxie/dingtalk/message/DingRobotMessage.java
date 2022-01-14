package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.dingtalk.dto.DingRobotAt;

/**
 * Created by EalenXie on 2021/12/27 9:23
 */
public abstract class DingRobotMessage {

    @JsonProperty("msgtype")
    private String msgType;

    private DingRobotAt at;

    public abstract String getMsgType();

    public DingRobotAt getAt() {
        return at;
    }

    public void setAt(DingRobotAt at) {
        this.at = at;
    }
}
