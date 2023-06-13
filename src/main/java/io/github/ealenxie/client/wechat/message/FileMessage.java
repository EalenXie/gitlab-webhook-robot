package io.github.ealenxie.client.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.client.wechat.dto.File;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/2/11 13:18
 */

public class FileMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "file";

    @Getter
    @Setter
    private File file;

    @Override
    public String getMsgType() {
        return msgType;
    }


}
