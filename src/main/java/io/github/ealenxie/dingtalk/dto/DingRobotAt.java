package io.github.ealenxie.dingtalk.dto;

import java.util.List;

/**
 * Created by EalenXie on 2021/10/25 11:42
 */
public class DingRobotAt {

    private List<String> atMobiles;

    private List<String> atUserIds;

    private Boolean isAtAll = Boolean.FALSE;


    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(List<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public List<String> getAtUserIds() {
        return atUserIds;
    }

    public void setAtUserIds(List<String> atUserIds) {
        this.atUserIds = atUserIds;
    }

    public Boolean getAtAll() {
        return isAtAll;
    }

    public void setAtAll(Boolean atAll) {
        isAtAll = atAll;
    }
}
