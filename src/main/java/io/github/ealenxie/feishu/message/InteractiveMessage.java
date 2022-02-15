package io.github.ealenxie.feishu.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.feishu.dto.Interactive;

/**
 * Created by EalenXie on 2022/2/15 12:56
 */
public class InteractiveMessage implements FeiShuMessage {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sign")
    private String sign;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("msg_type")
    private String msgType = "interactive";
    private Interactive card;

    public InteractiveMessage() {
    }

    public InteractiveMessage(Interactive card) {
        this.card = card;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getMsgType() {
        return msgType;
    }

    public Interactive getCard() {
        return card;
    }

    public void setCard(Interactive card) {
        this.card = card;
    }
}
