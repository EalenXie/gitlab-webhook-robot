package io.github.ealenxie.dingtalk.message;

import io.github.ealenxie.dingtalk.dto.FeedCard;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class FeedCardMessage extends DingRobotMessage {

    @Override
    public String getMsgType() {
        return "feedCard";
    }

    private FeedCard feedCard;

    public FeedCardMessage() {
    }

    public FeedCardMessage(FeedCard feedCard) {
        this.feedCard = feedCard;
    }

    public FeedCard getFeedCard() {
        return feedCard;
    }

    public void setFeedCard(FeedCard feedCard) {
        this.feedCard = feedCard;
    }
}
