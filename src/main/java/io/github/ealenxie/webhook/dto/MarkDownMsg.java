package io.github.ealenxie.webhook.dto;

import java.util.Collections;
import java.util.List;

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
     * 设置通知人
     */
    default List<String> notifier() {
        return Collections.emptyList();
    }

    /**
     * 生成markdown信息
     */
    String getMarkdown();
}
