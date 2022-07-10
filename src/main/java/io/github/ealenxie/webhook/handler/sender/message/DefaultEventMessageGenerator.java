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
 * 各种webhook事件 默认的消息生成器
 */
public class DefaultEventMessageGenerator implements EventMessageGenerator {
    @Override
    public EventMessage issueHookMessage(IssueHook issueHook) {
        return new DefaultIssueMessage();
    }

    @Override
    public EventMessage jobHookMessage(JobHook jobHook) {
        return new DefaultJobMessage();
    }

    @Override
    public EventMessage mergeRequestHookMessage(MergeRequestHook mergeRequestHook) {
        return new DefaultMergeRequestMessage();
    }

    @Override
    public EventMessage noteHookMessage(NoteHook noteHook) {
        return new DefaultNoteMessage();
    }

    @Override
    public EventMessage pipelineHookMessage(PipelineHook pipelineHook) {
        return new DefaultPipelineMessage();
    }

    @Override
    public EventMessage pushHookMessage(PushHook pushHook) {
        return new DefaultPushMessage();
    }

    @Override
    public EventMessage releaseHookMessage(ReleaseHook releaseHook) {
        return new DefaultReleaseMessage();
    }

    @Override
    public EventMessage tagPushHookMessage(TagPushHook tagPushHook) {
        return new DefaultTagPushMessage();
    }
}
