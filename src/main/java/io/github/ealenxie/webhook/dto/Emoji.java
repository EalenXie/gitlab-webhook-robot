package io.github.ealenxie.webhook.dto;

/**
 * Created by EalenXie on 2022/1/27 17:17
 */
public class Emoji {

    /**
     * 是否发送emoji标签
     */
    private static boolean enable = true;

    private String code;

    public Emoji(String code) {
        this.code = code;
    }


    public static void enableEmoji(boolean enableEmoji) {
        enable = enableEmoji;
    }


    @Override
    public String toString() {
        if (enable) {
            return code;
        } else {
            return "";
        }
    }


    public void setCode(String code) {
        if (enable) {
            this.code = code;
        } else {
            this.code = "";
        }
    }
}
