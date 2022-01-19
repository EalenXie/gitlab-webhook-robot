package io.github.ealenxie.dingtalk.message;

import io.github.ealenxie.dingtalk.dto.DingRobotAt;

/**
 * Created by EalenXie on 2021/12/27 9:23
 */
public abstract class DingRobotMessage {

    private DingRobotAt at;

    public DingRobotAt getAt() {
        return at;
    }

    public void setAt(DingRobotAt at) {
        this.at = at;
    }
}
