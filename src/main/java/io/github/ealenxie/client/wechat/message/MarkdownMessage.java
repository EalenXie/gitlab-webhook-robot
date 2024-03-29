package io.github.ealenxie.client.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.client.wechat.dto.Markdown;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */
@NoArgsConstructor
public class MarkdownMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "markdown";

    public String getMsgType() {
        return msgType;
    }

    @Getter
    @Setter
    private Markdown markdown;

    public MarkdownMessage(Markdown markdown) {
        this.markdown = markdown;
    }


}
