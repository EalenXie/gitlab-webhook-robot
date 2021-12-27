package io.github.ealenxie.webhook.dto;

import io.github.ealenxie.dingtalk.dto.Btn;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2021/12/27 11:32
 * 这是为了对接钉钉机器人的 ActionCard 固定设置
 */
public interface DingRobotActionCard {

    String getTitle();

    String getText();

    default List<Btn> getBtnList() {
        return Collections.emptyList();
    }

    default String getBtnOrientation() {
        return null;
    }

    default String getSingleTitle() {
        return null;
    }

    default String getSingleURL() {
        return null;
    }

}
