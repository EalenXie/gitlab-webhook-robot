package io.github.ealenxie.webhook.handler;

import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/10 13:59
 * Gitlab Webhook 事件处理器
 */
public interface GitlabWebhookEventHandler<R> {

    /**
     * 处理 IssueHook 事件消息
     */
    R issueEvent(WebhookDefinition webhook, IssueHook issueHook);

    /**
     * 处理 JobHook 事件消息
     */
    R jobEvent(WebhookDefinition webhook, JobHook jobHook);

    /**
     * 处理 MergeRequestHook 事件消息
     */
    R mergeRequestEvent(WebhookDefinition webhook, MergeRequestHook mergeRequestHook);

    /**
     * 处理 NoteHook 事件消息
     */
    R noteEvent(WebhookDefinition webhook, NoteHook noteHook);

    /**
     * 处理 PipelineHook 事件消息
     */
    R pipelineEvent(WebhookDefinition webhook, PipelineHook pipelineHook);

    /**
     * 处理 PushHook 事件消息
     */
    R pushEvent(WebhookDefinition webhook, PushHook pushHook);

    /**
     * 处理 ReleaseHook 事件消息
     */
    R releaseEvent(WebhookDefinition webhook, ReleaseHook releaseHook);

    /**
     * 处理 TagPushHook 事件消息
     */
    R tagPushEvent(WebhookDefinition webhook, TagPushHook tagPushHook);

}
