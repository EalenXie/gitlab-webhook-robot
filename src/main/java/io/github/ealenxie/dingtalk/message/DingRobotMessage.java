package io.github.ealenxie.dingtalk.message;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 9:23
 */
@Getter
@Setter
public abstract class DingRobotMessage {
    private DingRobotAt at;
}
