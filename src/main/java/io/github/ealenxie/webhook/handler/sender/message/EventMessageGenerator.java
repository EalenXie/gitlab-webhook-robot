package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;

/**
 * Created by EalenXie on 2022/7/10 15:34
 */
public interface EventMessageGenerator {

    /**
     * 获取 IssueHook 事件消息
     */
    EventMessage issueHookMessage(IssueHook issueHook);

    /**
     * 获取 JobHook 事件消息
     */
    EventMessage jobHookMessage(JobHook jobHook);

    /**
     * 获取 MergeRequestHook 事件消息
     */
    EventMessage mergeRequestHookMessage(MergeRequestHook mergeRequestHook);

    /**
     * 获取 NoteHook 事件消息
     */
    EventMessage noteHookMessage(NoteHook noteHook);

    /**
     * 获取 PipelineHook 事件消息
     */
    EventMessage pipelineHookMessage(PipelineHook pipelineHook);

    /**
     * 获取 PushHook 事件消息
     */
    EventMessage pushHookMessage(PushHook pushHook);

    /**
     * 获取 ReleaseHook 事件消息
     */
    EventMessage releaseHookMessage(ReleaseHook releaseHook);

    /**
     * 获取 TagPushHook 事件消息
     */
    EventMessage tagPushHookMessage(TagPushHook tagPushHook);
}
