package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
public class TextMessage implements WeChatMessage {

    @JsonProperty("msgtype")
    private String msgType = "text";
    private Text text;

    public String getMsgType() {
        return msgType;
    }

    public TextMessage() {
    }

    public TextMessage(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getMentionedList() {
            return mentionedList;
        }

        public void setMentionedList(List<String> mentionedList) {
            this.mentionedList = mentionedList;
        }

        public List<String> getMentionedMobileList() {
            return mentionedMobileList;
        }

        public void setMentionedMobileList(List<String> mentionedMobileList) {
            this.mentionedMobileList = mentionedMobileList;
        }

        public Text() {
        }

        public Text(String content, List<String> mentionedList, List<String> mentionedMobileList) {
            this.content = content;
            this.mentionedList = mentionedList;
            this.mentionedMobileList = mentionedMobileList;
        }
    }

}
