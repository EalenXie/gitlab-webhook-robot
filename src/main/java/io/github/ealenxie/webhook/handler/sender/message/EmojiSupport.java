package io.github.ealenxie.webhook.handler.sender.message;

/**
 * Created by EalenXie on 2022/7/10 16:05
 */
public interface EmojiSupport {

    default boolean enableEmoji() {
        return true;
    }
}
