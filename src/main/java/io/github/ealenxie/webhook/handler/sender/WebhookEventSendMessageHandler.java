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
import io.github.ealenxie.webhook.handler.sender.message.EventMessageGenerator;

/**
 * Created by EalenXie on 2022/7/10 16:59
 * webhook事件 发送消息处理器
 */
public interface WebhookEventSendMessageHandler<R> extends WebhookEventHandler<R> {


    R sendMessage(EventMessage m);

    EventMessageGenerator getEventMessageGenerator();

    /**
     * 处理 IssueHook 事件消息
     */
    default R issueEvent(IssueHook issueHook) {
        return sendMessage(getEventMessageGenerator().issueHookMessage(issueHook));
    }

    /**
     * 处理 JobHook 事件消息
     */
    default R jobEvent(JobHook jobHook) {
        return sendMessage(getEventMessageGenerator().jobHookMessage(jobHook));
    }

    /**
     * 处理 MergeRequestHook 事件消息
     */
    default R mergeRequestEvent(MergeRequestHook mergeRequestHook) {
        return sendMessage(getEventMessageGenerator().mergeRequestHookMessage(mergeRequestHook));
    }

    /**
     * 处理 NoteHook 事件消息
     */
    default R noteEvent(NoteHook noteHook) {
        return sendMessage(getEventMessageGenerator().noteHookMessage(noteHook));
    }

    /**
     * 处理 PipelineHook 事件消息
     */
    default R pipelineEvent(PipelineHook pipelineHook) {
        return sendMessage(getEventMessageGenerator().pipelineHookMessage(pipelineHook));
    }

    /**
     * 处理 PushHook 事件消息
     */
    default R pushEvent(PushHook pushHook) {
        return sendMessage(getEventMessageGenerator().pushHookMessage(pushHook));
    }

    /**
     * 处理 ReleaseHook 事件消息
     */
    default R releaseEvent(ReleaseHook releaseHook) {
        return sendMessage(getEventMessageGenerator().releaseHookMessage(releaseHook));
    }

    /**
     * 处理 TagPushHook 事件消息
     */
    default R tagPushEvent(TagPushHook tagPushHook) {
        return sendMessage(getEventMessageGenerator().tagPushHookMessage(tagPushHook));
    }

}
