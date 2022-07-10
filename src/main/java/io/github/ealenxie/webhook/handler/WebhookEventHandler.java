package io.github.ealenxie.webhook.handler;

import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;

/**
 * Created by EalenXie on 2022/7/10 13:59
 * webhook 事件处理器
 */
public interface WebhookEventHandler<R> {

    /**
     * 处理 IssueHook 事件消息
     */
    R issueEvent(IssueHook issueHook);

    /**
     * 处理 JobHook 事件消息
     */
    R jobEvent(JobHook jobHook);

    /**
     * 处理 MergeRequestHook 事件消息
     */
    R mergeRequestEvent(MergeRequestHook mergeRequestHook);

    /**
     * 处理 NoteHook 事件消息
     */
    R noteEvent(NoteHook noteHook);

    /**
     * 处理 PipelineHook 事件消息
     */
    R pipelineEvent(PipelineHook pipelineHook);

    /**
     * 处理 PushHook 事件消息
     */
    R pushEvent(PushHook pushHook);

    /**
     * 处理 ReleaseHook 事件消息
     */
    R releaseEvent(ReleaseHook releaseHook);

    /**
     * 处理 TagPushHook 事件消息
     */
    R tagPushEvent(TagPushHook tagPushHook);

}
