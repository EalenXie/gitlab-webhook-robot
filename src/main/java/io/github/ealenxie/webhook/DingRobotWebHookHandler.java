package io.github.ealenxie.webhook;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.dingtalk.DingRobotAPI;
import io.github.ealenxie.dingtalk.dto.ActionCard;
import io.github.ealenxie.dingtalk.message.ActionCardMessage;
import io.github.ealenxie.webhook.conf.DingRobotConfig;
import io.github.ealenxie.webhook.dto.DingRobotActionCard;
import io.github.ealenxie.webhook.dto.ObjectAttributes;
import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagHook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/27 11:23
 * 钉钉机器人处理 webhook事件
 */
@Service
public class DingRobotWebHookHandler implements WebHookHandler<JsonNode, ResponseEntity<String>> {

    @Resource
    private DingRobotConfig dingRobotConfig;
    public static final ObjectMapper OBJECTMapper;
    private static final String PUSH_HOOK = "Push Hook";
    private static final String PIPELINE_HOOK = "Pipeline Hook";
    private static final String MERGE_REQUEST_HOOK = "Merge Request Hook";
    private static final String ISSUE_HOOK = "Issue Hook";
    private static final String RELEASE_HOOK = "Release Hook";
    private static final String NOTE_HOOK = "Note Hook";
    private static final String JOB_HOOK = "Job Hook";
    private static final String TAG_PUSH_HOOK = "Tag Push Hook";
    private static final String ACTION_UPDATE = "update";

    static {
        OBJECTMapper = new ObjectMapper();
        OBJECTMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public ResponseEntity<String> handle(JsonNode body, String event) {
        switch (event) {
            case PUSH_HOOK:
                PushHook pushHookVO = OBJECTMapper.convertValue(body, PushHook.class);
                if (!pushHookVO.getCommits().isEmpty()) {
                    return callDingRobotActionCard(pushHookVO);
                }
                break;
            case PIPELINE_HOOK:
                PipelineHook pipelineHookVO = OBJECTMapper.convertValue(body, PipelineHook.class);
                ObjectAttributes objectAttributes = pipelineHookVO.getObjectAttributes();
                if (objectAttributes != null && !"pending".equals(objectAttributes.getStatus())) {
                    return callDingRobotActionCard(pipelineHookVO);
                }
                break;
            case MERGE_REQUEST_HOOK:
                MergeRequestHook mergeRequestHookVO = OBJECTMapper.convertValue(body, MergeRequestHook.class);
                String action = mergeRequestHookVO.getObjectAttributes().getAction();
                if (action != null && !ACTION_UPDATE.equals(action)) {
                    return callDingRobotActionCard(mergeRequestHookVO);
                }
                break;
            case ISSUE_HOOK:
                IssueHook issueHookVO = OBJECTMapper.convertValue(body, IssueHook.class);
                String issueAction = issueHookVO.getObjectAttributes().getAction();
                if (!ACTION_UPDATE.equals(issueAction)) {
                    return callDingRobotActionCard(issueHookVO);
                }
                break;
            case RELEASE_HOOK:
                ReleaseHook releaseHookVO = OBJECTMapper.convertValue(body, ReleaseHook.class);
                String releaseAction = releaseHookVO.getAction();
                if (!ACTION_UPDATE.equals(releaseAction)) {
                    return callDingRobotActionCard(releaseHookVO);
                }
                break;
            case NOTE_HOOK:
                NoteHook noteHookVO = OBJECTMapper.convertValue(body, NoteHook.class);
                return callDingRobotActionCard(noteHookVO);
            case TAG_PUSH_HOOK:
                TagHook tagHookVO = OBJECTMapper.convertValue(body, TagHook.class);
                if ("tag_push".equals(tagHookVO.getObjectKind())) {
                    return callDingRobotActionCard(tagHookVO);
                }
                break;
            case JOB_HOOK:
                JobHook jobHook = OBJECTMapper.convertValue(body, JobHook.class);
                return callDingRobotActionCard(jobHook);
            default:
                return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok().body(null);
    }


    private ResponseEntity<String> callDingRobotActionCard(DingRobotActionCard dto) {
        ActionCard actionCard = new ActionCard();
        actionCard.setTitle(dto.getTitle());
        actionCard.setText(dto.getText());
        actionCard.setBtnOrientation(dto.getBtnOrientation());
        actionCard.setBtnList(dto.getBtnList());
        ActionCardMessage actionCardMessage = new ActionCardMessage(actionCard);
        return DingRobotAPI.callDingRobot(dingRobotConfig.getUrl(), actionCardMessage, dingRobotConfig.getAccessToken(), dingRobotConfig.getSignKey());
    }
}
