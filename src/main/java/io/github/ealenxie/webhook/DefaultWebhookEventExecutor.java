package io.github.ealenxie.webhook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.webhook.dto.issue.IssueHook;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.dto.note.NoteHook;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;
import io.github.ealenxie.webhook.handler.WebhookEventHandler;
import io.github.ealenxie.webhook.handler.sender.DingSendMessageHandler;
import io.github.ealenxie.webhook.handler.sender.FeishuSendMessageHandler;
import io.github.ealenxie.webhook.handler.sender.WechatSendMessageHandler;
import io.github.ealenxie.webhook.meta.WebhookDefinition;
import io.github.ealenxie.webhook.meta.WebhookWay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.ObjectUtils;

/**
 * Created by EalenXie on 2022/7/10 13:59
 * webhook 事件处理 执行器
 */
public class DefaultWebhookEventExecutor implements WebhookEventExecutor<Object>, ApplicationContextAware {

    private static final String PUSH_HOOK = "Push Hook";
    private static final String PIPELINE_HOOK = "Pipeline Hook";
    private static final String MERGE_REQUEST_HOOK = "Merge Request Hook";
    private static final String ISSUE_HOOK = "Issue Hook";
    private static final String RELEASE_HOOK = "Release Hook";
    private static final String NOTE_HOOK = "Note Hook";
    private static final String JOB_HOOK = "Job Hook";
    private static final String TAG_PUSH_HOOK = "Tag Push Hook";
    private static final String ACTION_UPDATE = "update";
    private ApplicationContext applicationContext;
    public final ObjectMapper objectMapper;

    public DefaultWebhookEventExecutor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object execute(WebhookDefinition webhook, @NonNull String event, @NonNull JsonNode body) {
        WebhookEventHandler<?> webhookEventHandler = getByWebhook(webhook);
        switch (event) {
            case PUSH_HOOK:
                PushHook pushHook = objectMapper.convertValue(body, PushHook.class);
                if (!pushHook.getCommits().isEmpty()) {
                    return webhookEventHandler.pushEvent(webhook, pushHook);
                }
                break;
            case PIPELINE_HOOK:
                PipelineHook pipelineHook = objectMapper.convertValue(body, PipelineHook.class);
                PipelineHook.ObjectAttributes objectAttributes = pipelineHook.getObjectAttributes();
                if (objectAttributes != null && !"pending".equals(objectAttributes.getStatus())) {
                    return webhookEventHandler.pipelineEvent(webhook, pipelineHook);
                }
                break;
            case MERGE_REQUEST_HOOK:
                MergeRequestHook mergeRequestHook = objectMapper.convertValue(body, MergeRequestHook.class);
                String action = mergeRequestHook.getObjectAttributes().getAction();
                if (action != null && !ACTION_UPDATE.equals(action)) {
                    return webhookEventHandler.mergeRequestEvent(webhook, mergeRequestHook);
                }
                break;
            case ISSUE_HOOK:
                IssueHook issueHook = objectMapper.convertValue(body, IssueHook.class);
                String issueAction = issueHook.getObjectAttributes().getAction();
                if (!ACTION_UPDATE.equals(issueAction)) {
                    return webhookEventHandler.issueEvent(webhook, issueHook);
                }
                break;
            case RELEASE_HOOK:
                ReleaseHook releaseHook = objectMapper.convertValue(body, ReleaseHook.class);
                String releaseAction = releaseHook.getAction();
                if (!ACTION_UPDATE.equals(releaseAction)) {
                    return webhookEventHandler.releaseEvent(webhook, releaseHook);
                }
                break;
            case NOTE_HOOK:
                NoteHook noteHook = objectMapper.convertValue(body, NoteHook.class);
                return webhookEventHandler.noteEvent(webhook, noteHook);
            case TAG_PUSH_HOOK:
                TagPushHook tagPushHook = objectMapper.convertValue(body, TagPushHook.class);
                if ("tag_push".equals(tagPushHook.getObjectKind())) {
                    return webhookEventHandler.tagPushEvent(webhook, tagPushHook);
                }
                break;
            case JOB_HOOK:
                JobHook jobHook = objectMapper.convertValue(body, JobHook.class);
                return webhookEventHandler.jobEvent(webhook, jobHook);
            default:
                return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok().body(null);
    }


    @Override
    public WebhookEventHandler<Object> getByWebhook(WebhookDefinition webhook) {
        WebhookWay webhookWay = webhook.getWay();
        if (ObjectUtils.isEmpty(webhook.getConfig())) {
            throw new UnsupportedOperationException("config is required to enable this feature");
        }
        switch (webhookWay) {
            case DING_MESSAGE:
                return applicationContext.getBean(DingSendMessageHandler.class);
            case FEI_SHU_MESSAGE:
                return applicationContext.getBean(FeishuSendMessageHandler.class);
            case WECHAT_MESSAGE:
                return applicationContext.getBean(WechatSendMessageHandler.class);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
