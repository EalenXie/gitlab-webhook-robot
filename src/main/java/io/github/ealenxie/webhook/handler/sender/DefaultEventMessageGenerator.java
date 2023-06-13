package io.github.ealenxie.webhook.handler.sender;

import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;
import io.github.ealenxie.webhook.handler.sender.message.*;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

/**
 * Created by EalenXie on 2022/7/10 15:34
 * 各种webhook事件 默认的消息生成器
 */
public class DefaultEventMessageGenerator implements EventMessageGenerator {
    @Override
    public EventMessage issueHookMessage(WebhookDefinition webhook, IssueHook issueHook) {
        return new DefaultIssueMessage(webhook, issueHook);
    }

    @Override
    public EventMessage jobHookMessage(WebhookDefinition webhook, JobHook jobHook) {
        return new DefaultJobMessage(webhook, jobHook);
    }

    @Override
    public EventMessage mergeRequestHookMessage(WebhookDefinition webhook, MergeRequestHook mergeRequestHook) {
        return new DefaultMergeRequestMessage(webhook, mergeRequestHook);
    }

    @Override
    public EventMessage noteHookMessage(WebhookDefinition webhook, NoteHook noteHook) {
        return new DefaultNoteMessage(webhook, noteHook);
    }

    @Override
    public EventMessage pipelineHookMessage(WebhookDefinition webhook, PipelineHook pipelineHook) {
        return new DefaultPipelineMessage(webhook, pipelineHook);
    }

    @Override
    public EventMessage pushHookMessage(WebhookDefinition webhook, PushHook pushHook) {
        return new DefaultPushMessage(webhook, pushHook);
    }

    @Override
    public EventMessage releaseHookMessage(WebhookDefinition webhook, ReleaseHook releaseHook) {
        return new DefaultReleaseMessage(webhook, releaseHook);
    }

    @Override
    public EventMessage tagPushHookMessage(WebhookDefinition webhook, TagPushHook tagPushHook) {
        return new DefaultTagPushMessage(webhook, tagPushHook);
    }
}
