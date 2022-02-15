package io.github.ealenxie.wechat.message;

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
public class TextMessage implements WeChatMessage {

    @JsonProperty("msgtype")
    private String msgType = "text";
    @Getter
    @Setter
    private Text text;

    public String getMsgType() {
        return msgType;
    }

    public TextMessage(Text text) {
        this.text = text;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Text {

        @JsonProperty("content")
        private String content;
        /**
         * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用mentioned_mobile_list
         */
        @JsonProperty("mentioned_list")
        private List<String> mentionedList;
        /**
         * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
         */
        @JsonProperty("mentioned_mobile_list")
        private List<String> mentionedMobileList;

    }

}
