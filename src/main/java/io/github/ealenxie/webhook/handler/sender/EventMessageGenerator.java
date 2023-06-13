package io.github.ealenxie.webhook.handler.sender;

import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;
import io.github.ealenxie.webhook.handler.sender.message.EventMessage;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/10 15:34
 */
public interface EventMessageGenerator {

    /**
     * 获取 IssueHook 事件消息
     */
    EventMessage issueHookMessage(WebhookDefinition webhook,IssueHook issueHook);

    /**
     * 获取 JobHook 事件消息
     */
    EventMessage jobHookMessage(WebhookDefinition webhook,JobHook jobHook);

    /**
     * 获取 MergeRequestHook 事件消息
     */
    EventMessage mergeRequestHookMessage(WebhookDefinition webhook,MergeRequestHook mergeRequestHook);

    /**
     * 获取 NoteHook 事件消息
     */
    EventMessage noteHookMessage(WebhookDefinition webhook,NoteHook noteHook);

    /**
     * 获取 PipelineHook 事件消息
     */
    EventMessage pipelineHookMessage(WebhookDefinition webhook, PipelineHook pipelineHook);

    /**
     * 获取 PushHook 事件消息
     */
    EventMessage pushHookMessage(WebhookDefinition webhook,PushHook pushHook);

    /**
     * 获取 ReleaseHook 事件消息
     */
    EventMessage releaseHookMessage(WebhookDefinition webhook,ReleaseHook releaseHook);

    /**
     * 获取 TagPushHook 事件消息
     */
    EventMessage tagPushHookMessage(WebhookDefinition webhook,TagPushHook tagPushHook);
}
