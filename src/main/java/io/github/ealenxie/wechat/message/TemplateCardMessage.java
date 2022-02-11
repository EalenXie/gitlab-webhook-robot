package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2022/2/11 15:58
 */
public class TemplateCardMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "template_card";

    public String getMsgType() {
        return msgType;
    }
}
