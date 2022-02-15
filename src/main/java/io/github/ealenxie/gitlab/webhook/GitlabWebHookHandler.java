package io.github.ealenxie.gitlab.webhook;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.gitlab.webhook.dto.MarkDownMsg;
import io.github.ealenxie.gitlab.webhook.dto.issue.IssueHook;
import io.github.ealenxie.gitlab.webhook.dto.job.JobHook;
import io.github.ealenxie.gitlab.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.gitlab.webhook.dto.note.NoteHook;
import io.github.ealenxie.gitlab.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.gitlab.webhook.dto.push.PushHook;
import io.github.ealenxie.gitlab.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.gitlab.webhook.dto.tag.TagHook;
import io.github.ealenxie.gitlab.webhook.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Created by EalenXie on 2022/2/11 16:20
 */
@Service
public class GitlabWebHookHandler implements WebHookHandler<JsonNode, ResponseEntity<String>> {

    private static final String PUSH_HOOK = "Push Hook";
    private static final String PIPELINE_HOOK = "Pipeline Hook";
    private static final String MERGE_REQUEST_HOOK = "Merge Request Hook";
    private static final String ISSUE_HOOK = "Issue Hook";
    private static final String RELEASE_HOOK = "Release Hook";
    private static final String NOTE_HOOK = "Note Hook";
    private static final String JOB_HOOK = "Job Hook";
    private static final String TAG_PUSH_HOOK = "Tag Push Hook";
    private static final String ACTION_UPDATE = "update";
    public final ObjectMapper objectMapper;

    private final MessageSender<MarkDownMsg, String> messageSender;

    public GitlabWebHookHandler(@Autowired MessageSender<MarkDownMsg, String> messageSender) {
        this.messageSender = messageSender;
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 处理webhook事件
     *
     * @param body  消息请求体
     * @param event webhook 事件
     * @return 响应
     */
    @Override
    public ResponseEntity<String> handle(@NonNull JsonNode body, @NonNull String event) {
        switch (event) {
            case PUSH_HOOK:
                PushHook pushHook = objectMapper.convertValue(body, PushHook.class);
                if (!pushHook.getCommits().isEmpty()) {
                    return messageSender.sendMessage(pushHook);
                }
                break;
            case PIPELINE_HOOK:
                PipelineHook pipelineHook = objectMapper.convertValue(body, PipelineHook.class);
                PipelineHook.ObjectAttributes objectAttributes = pipelineHook.getObjectAttributes();
                if (objectAttributes != null && !"pending".equals(objectAttributes.getStatus())) {
                    return messageSender.sendMessage(pipelineHook);
                }
                break;
            case MERGE_REQUEST_HOOK:
                MergeRequestHook mergeRequestHook = objectMapper.convertValue(body, MergeRequestHook.class);
                String action = mergeRequestHook.getObjectAttributes().getAction();
                if (action != null && !ACTION_UPDATE.equals(action)) {
                    return messageSender.sendMessage(mergeRequestHook);
                }
                break;
            case ISSUE_HOOK:
                IssueHook issueHook = objectMapper.convertValue(body, IssueHook.class);
                String issueAction = issueHook.getObjectAttributes().getAction();
                if (!ACTION_UPDATE.equals(issueAction)) {
                    return messageSender.sendMessage(issueHook);
                }
                break;
            case RELEASE_HOOK:
                ReleaseHook releaseHook = objectMapper.convertValue(body, ReleaseHook.class);
                String releaseAction = releaseHook.getAction();
                if (!ACTION_UPDATE.equals(releaseAction)) {
                    return messageSender.sendMessage(releaseHook);
                }
                break;
            case NOTE_HOOK:
                NoteHook noteHook = objectMapper.convertValue(body, NoteHook.class);
                return messageSender.sendMessage(noteHook);
            case TAG_PUSH_HOOK:
                TagHook tagHook = objectMapper.convertValue(body, TagHook.class);
                if ("tag_push".equals(tagHook.getObjectKind())) {
                    return messageSender.sendMessage(tagHook);
                }
                break;
            case JOB_HOOK:
                JobHook jobHook = objectMapper.convertValue(body, JobHook.class);
                return messageSender.sendMessage(jobHook);
            default:
                return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok().body(null);
    }


}
