package io.github.ealenxie.feishu.message;

/**
 * Created by EalenXie on 2022/2/14 17:15
 */
public interface FeiShuMessage {

    String getMsgType();

    default void setSign(String sign) {
    }

    default void setTimestamp(String timestamp) {
    }


}
