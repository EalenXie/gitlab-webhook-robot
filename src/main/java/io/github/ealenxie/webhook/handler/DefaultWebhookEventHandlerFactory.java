package io.github.ealenxie.webhook.handler;

import io.github.ealenxie.webhook.handler.sender.DingSendMessageHandler;
import io.github.ealenxie.webhook.handler.sender.FeishuSendMessageHandler;
import io.github.ealenxie.webhook.handler.sender.WechatSendMessageHandler;
import io.github.ealenxie.webhook.meta.WebhookWay;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by EalenXie on 2022/7/10 14:53
 * webhook 工厂
 */
public class DefaultWebhookEventHandlerFactory implements ApplicationContextAware, WebhookEventHandlerFactory<String> {

    private ApplicationContext applicationContext;

    @Override
    public WebhookEventHandler<String> getByWebhookWay(WebhookWay webhookWay) {
        switch (webhookWay) {
            case DING_MESSAGE:
                return applicationContext.getBean(DingSendMessageHandler.class);
            case FEISHU_MESSAGE:
                return applicationContext.getBean(FeishuSendMessageHandler.class);
            case WECHAT_MESSAGE:
                return applicationContext.getBean(WechatSendMessageHandler.class);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
