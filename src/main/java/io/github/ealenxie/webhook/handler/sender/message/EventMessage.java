package io.github.ealenxie.webhook.handler.sender.message;

import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:26
 * webhook 事件消息
 */
public interface EventMessage {


    /**
     * 消息title
     */
    String title();

    /**
     * 消息内容
     */
    String message();

    /**
     * 消息通知人
     */
    List<String> notifies();


}
