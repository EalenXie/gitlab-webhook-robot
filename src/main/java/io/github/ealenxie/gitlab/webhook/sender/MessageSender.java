package io.github.ealenxie.gitlab.webhook.sender;

import org.springframework.http.ResponseEntity;

/**
 * Created by EalenXie on 2022/2/11 16:16
 */
public interface MessageSender<M, R> {


    /**
     * 发送消息
     *
     * @param m 消息
     * @return 响应
     */
    ResponseEntity<R> sendMessage(M m);

}
