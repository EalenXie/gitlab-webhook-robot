package io.github.ealenxie.gitlab.webhook;

/**
 * Created by EalenXie on 2021/12/27 11:19
 */
public interface WebHookHandler<B, R> {


    /**
     * 处理webhook事件
     *
     * @param body  消息请求体
     * @param event webhook 事件
     * @return 响应
     */
    R handle(B body, String event);

}
