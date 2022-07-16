package io.github.ealenxie.webhook.handler.sender.message;


import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:26
 * webhook 事件消息
 */
public interface EventMessage {


    WebhookDefinition webhook();

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
