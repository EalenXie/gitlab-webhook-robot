package io.github.ealenxie.gitlab.webhook;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.dingtalk.DingRobotClient;
import io.github.ealenxie.dingtalk.dto.DingRobotAt;
import io.github.ealenxie.dingtalk.dto.Markdown;
import io.github.ealenxie.dingtalk.message.MarkdownMessage;
import io.github.ealenxie.gitlab.webhook.conf.DingRobotConfig;
import io.github.ealenxie.gitlab.webhook.dto.MarkDownMsg;
import io.github.ealenxie.gitlab.webhook.dto.ObjectAttributes;
import io.github.ealenxie.gitlab.webhook.dto.issue.IssueHook;
import io.github.ealenxie.gitlab.webhook.dto.job.JobHook;
import io.github.ealenxie.gitlab.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.gitlab.webhook.dto.note.NoteHook;
import io.github.ealenxie.gitlab.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.gitlab.webhook.dto.push.PushHook;
import io.github.ealenxie.gitlab.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.gitlab.webhook.dto.tag.TagHook;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/27 11:23
 * 钉钉机器人处理 webhook事件
 */
@Service
public class DingRobotWebHookHandler implements WebHookHandler<JsonNode, ResponseEntity<String>> {

    private static final String PUSH_HOOK = "Push Hook";
    private static final String PIPELINE_HOOK = "Pipeline Hook";
    private static final String MERGE_REQUEST_HOOK = "Merge Request Hook";
    private static final String ISSUE_HOOK = "Issue Hook";
    private static final String RELEASE_HOOK = "Release Hook";
    private static final String NOTE_HOOK = "Note Hook";
    private static final String JOB_HOOK = "Job Hook";
    private static final String TAG_PUSH_HOOK = "Tag Push Hook";
    private static final String ACTION_UPDATE = "update";

    @Resource
    private DingRobotConfig dingRobotConfig;

    private final DingRobotClient dingRobotClient;

    public final ObjectMapper objectMapper;

    public DingRobotWebHookHandler(RestTemplate httpClientRestTemplate) {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        dingRobotClient = new DingRobotClient(httpClientRestTemplate);
    }

    private ResponseEntity<String> callDingRobot(@NonNull MarkDownMsg msg) {
        Markdown markdown = new Markdown();
        markdown.setTitle(msg.getTitle());
        markdown.setText(msg.getMarkdown());
        MarkdownMessage actionCardMessage = new MarkdownMessage(markdown);
        if (!msg.notifier().isEmpty()) {
            DingRobotAt at = new DingRobotAt();
            at.setAtMobiles(msg.notifier());
            actionCardMessage.setAt(at);
        }
        return dingRobotClient.callDingRobot(dingRobotConfig.getUrl(), actionCardMessage, dingRobotConfig.getAccessToken(), dingRobotConfig.getSignKey());
    }

    @Override
    public ResponseEntity<String> handle(@NonNull JsonNode body, @NonNull String event) {
        switch (event) {
            case PUSH_HOOK:
                PushHook pushHook = objectMapper.convertValue(body, PushHook.class);
                if (!pushHook.getCommits().isEmpty()) {
                    return callDingRobot(pushHook);
                }
                break;
            case PIPELINE_HOOK:
                PipelineHook pipelineHook = objectMapper.convertValue(body, PipelineHook.class);
                ObjectAttributes objectAttributes = pipelineHook.getObjectAttributes();
                if (objectAttributes != null && !"pending".equals(objectAttributes.getStatus())) {
                    return callDingRobot(pipelineHook);
                }
                break;
            case MERGE_REQUEST_HOOK:
                MergeRequestHook mergeRequestHook = objectMapper.convertValue(body, MergeRequestHook.class);
                String action = mergeRequestHook.getObjectAttributes().getAction();
                if (action != null && !ACTION_UPDATE.equals(action)) {
                    return callDingRobot(mergeRequestHook);
                }
                break;
            case ISSUE_HOOK:
                IssueHook issueHook = objectMapper.convertValue(body, IssueHook.class);
                String issueAction = issueHook.getObjectAttributes().getAction();
                if (!ACTION_UPDATE.equals(issueAction)) {
                    return callDingRobot(issueHook);
                }
                break;
            case RELEASE_HOOK:
                ReleaseHook releaseHook = objectMapper.convertValue(body, ReleaseHook.class);
                String releaseAction = releaseHook.getAction();
                if (!ACTION_UPDATE.equals(releaseAction)) {
                    return callDingRobot(releaseHook);
                }
                break;
            case NOTE_HOOK:
                NoteHook noteHook = objectMapper.convertValue(body, NoteHook.class);
                return callDingRobot(noteHook);
            case TAG_PUSH_HOOK:
                TagHook tagHook = objectMapper.convertValue(body, TagHook.class);
                if ("tag_push".equals(tagHook.getObjectKind())) {
                    return callDingRobot(tagHook);
                }
                break;
            case JOB_HOOK:
                JobHook jobHook = objectMapper.convertValue(body, JobHook.class);
                return callDingRobot(jobHook);
            default:
                return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok().body(null);
    }

}
