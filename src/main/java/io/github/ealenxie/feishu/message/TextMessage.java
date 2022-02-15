package io.github.ealenxie.feishu.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.feishu.dto.Content;

/**
 * Created by EalenXie on 2022/2/14 17:16
 */
public class TextMessage implements FeiShuMessage {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sign")
    private String sign;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("msg_type")
    private String msgType = "text";
    @JsonProperty("content")
    private Content content;

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

    public TextMessage(Content content) {
        this.content = content;
    }

    public String getMsgType() {
        return msgType;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
