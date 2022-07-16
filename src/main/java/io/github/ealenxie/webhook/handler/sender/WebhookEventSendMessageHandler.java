package io.github.ealenxie.webhook.handler.sender;

import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;
import io.github.ealenxie.webhook.handler.WebhookEventHandler;
import io.github.ealenxie.webhook.handler.sender.message.EventMessage;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/10 16:59
 * webhook事件 发送消息处理器
 */
public interface WebhookEventSendMessageHandler<R> extends WebhookEventHandler<R> {

    EventMessageGenerator getEventMessageGenerator();


    R sendMessage(EventMessage m);

    /**
     * 处理 IssueHook 事件消息
     */
    default R issueEvent(WebhookDefinition webhook, IssueHook issueHook) {
        return sendMessage(getEventMessageGenerator().issueHookMessage(webhook, issueHook));
    }

    /**
     * 处理 JobHook 事件消息
     */
    default R jobEvent(WebhookDefinition webhook, JobHook jobHook) {
        return sendMessage(getEventMessageGenerator().jobHookMessage(webhook, jobHook));
    }

    /**
     * 处理 MergeRequestHook 事件消息
     */
    default R mergeRequestEvent(WebhookDefinition webhook, MergeRequestHook mergeRequestHook) {
        return sendMessage(getEventMessageGenerator().mergeRequestHookMessage(webhook, mergeRequestHook));
    }

    /**
     * 处理 NoteHook 事件消息
     */
    default R noteEvent(WebhookDefinition webhook, NoteHook noteHook) {
        return sendMessage(getEventMessageGenerator().noteHookMessage(webhook, noteHook));
    }

    /**
     * 处理 PipelineHook 事件消息
     */
    default R pipelineEvent(WebhookDefinition webhook, PipelineHook pipelineHook) {
        return sendMessage(getEventMessageGenerator().pipelineHookMessage(webhook, pipelineHook));
    }

    /**
     * 处理 PushHook 事件消息
     */
    default R pushEvent(WebhookDefinition webhook, PushHook pushHook) {
        return sendMessage(getEventMessageGenerator().pushHookMessage(webhook, pushHook));
    }

    /**
     * 处理 ReleaseHook 事件消息
     */
    default R releaseEvent(WebhookDefinition webhook, ReleaseHook releaseHook) {
        return sendMessage(getEventMessageGenerator().releaseHookMessage(webhook, releaseHook));
    }

    /**
     * 处理 TagPushHook 事件消息
     */
    default R tagPushEvent(WebhookDefinition webhook, TagPushHook tagPushHook) {
        return sendMessage(getEventMessageGenerator().tagPushHookMessage(webhook, tagPushHook));
    }

}
