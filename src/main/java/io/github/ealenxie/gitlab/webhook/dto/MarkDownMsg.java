package io.github.ealenxie.gitlab.webhook.dto;

/**
 * Created by EalenXie on 2021/12/1 9:46
 * 生成markdown消息
 */
public interface MarkDownMsg {

    /**
     * 消息标题
     */
    default String getTitle() {
        return "undefined";
    }

    /**
     * 生成markdown信息
     */
    String getMarkdown();
}
