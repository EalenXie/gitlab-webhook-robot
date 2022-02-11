package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.wechat.dto.File;

/**
 * Created by EalenXie on 2022/2/11 13:18
 */
public class FileMessage implements WeChatMessage {
    @JsonProperty("msgtype")
    private String msgType = "file";

    private File file;

    @Override
    public String getMsgType() {
        return msgType;
    }

    public FileMessage() {
    }

    public FileMessage(File file) {
        this.file = file;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
