package io.github.ealenxie.dingtalk.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class FeedCard {
        private List<Link> links;

        @NoArgsConstructor
        @AllArgsConstructor
        @Setter
        @Getter
        public static class Link {
            private String text;
            private String title;
            private String picUrl;
            private String messageUrl;
        }
    }
}
