package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.GitlabEndpoint;
import io.github.ealenxie.webhook.dto.Build;
import io.github.ealenxie.webhook.dto.Commit;
import io.github.ealenxie.webhook.dto.Project;
import io.github.ealenxie.webhook.dto.pipeline.PipelineHook;
import io.github.ealenxie.webhook.meta.WebhookDefinition;
import io.github.ealenxie.webhook.tool.FileConvert;
import io.github.ealenxie.webhook.tool.SpringEnvHelper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultPipelineMessage extends WebhookMessage {

    private final PipelineHook pipelineHook;

    public DefaultPipelineMessage(WebhookDefinition webhook, PipelineHook pipelineHook) {
        super(webhook);
        this.pipelineHook = pipelineHook;
    }

    @Override
    public String title() {
        return pipelineHook.getObjectKind();
    }

    @Override
    @SuppressWarnings("all")
    public String message() {
        StringBuilder sb = new StringBuilder();
        PipelineHook.ObjectAttributes objectAttributes = pipelineHook.getObjectAttributes();
        Project project = pipelineHook.getProject();
        Commit commit = pipelineHook.getCommit();
        List<Build> builds = pipelineHook.getBuilds();
        String status = objectAttributes.getStatus();
        String pipeline = String.format("%s [#%s %s](%s/-/pipelines/%s)", pipelineHook.getObjectKind(), objectAttributes.getId(), enableEmoji() ? "\uD83D\uDE80" : "", project.getWebUrl(), objectAttributes.getId());
        sb.append(String.format("[[%s:%s]](%s/-/tree/%s) <font color='#000000'>%s %s</font>%n%n", project.getName(), objectAttributes.getRef(), project.getWebUrl(), objectAttributes.getRef(), pipeline, status));
        if (!"running".equals(status)) {
            int totalTime = 0;
            if (objectAttributes.getDuration() != null) {
                totalTime += objectAttributes.getDuration();
            }
            if (objectAttributes.getQueuedDuration() != null) {
                totalTime += objectAttributes.getQueuedDuration();
            }
            sb.append(String.format(">[%s](%s) %s - %s%n%n", commit.getId().substring(0, 8), commit.getUrl(), commit.getAuthor().getName(), commit.getTitle()));
            String statusEmoji = "";
            String statusColor = "";
            switch (status) {
                case "success":
                    statusEmoji = "✅";
                    statusColor = "#00b140";
                    break;
                case "failed":
                    statusEmoji = "❌";
                    statusColor = "#ff0000";
                    break;
                case "canceled":
                    statusEmoji = "⏹️";
                    statusColor = "#FFDAC8";
                    break;
                case "skipped":
                    statusEmoji = "⏭️";
                    statusColor = "#8E8E8E";
                    break;
                default:
                    break;
            }
            statusEmoji = enableEmoji() ? statusEmoji : "";
            sb.append(String.format("%s%s : <font color='%s'>%s</font> %s %ss%n%n", statusEmoji, pipeline, statusColor, objectAttributes.getDetailedStatus(), enableEmoji() ? "\uD83D\uDD57" : "", totalTime));
            Collections.sort(builds);
            for (Build build : builds) {
                String costTime = String.format("%.0f", build.getDuration());
                if (costTime.equals("")) {
                    if (build.getFinishedAt() != null && build.getStartedAt() != null) {
                        Date finishedAt = Date.from(LocalDateTime.parse(build.getFinishedAt().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
                        Date start = Date.from(LocalDateTime.parse(build.getStartedAt().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
                        costTime = String.valueOf((finishedAt.getTime() - start.getTime()) / 1000);
                    } else {
                        costTime = "0";
                    }
                }
                String color = "";
                String emoji = "";
                switch (build.getStatus()) {
                    case "success":
                        color = "#00b140";
                        emoji = "✔️";
                        break;
                    case "failed":
                        color = "#ff0000";
                        emoji = "❌";
                        break;
                    case "canceled":
                        color = "#FFDAC8";
                        emoji = "⏹️";
                        break;
                    case "skipped":
                        color = "#8E8E8E";
                        emoji = "⏭️";
                        break;
                    default:
                        break;
                }
                emoji = enableEmoji() ? emoji : "";
                String fileName = "";
                if (build.getArtifactFile().getFilename() != null && build.getArtifactFile().getSize() != null) {
                    fileName = String.format("[%s](%s/-/jobs/%s/artifacts/download) %s", build.getArtifactFile().getFilename(), project.getWebUrl(), build.getId(), FileConvert.getFormatFileSize(build.getArtifactFile().getSize()));
                }
                sb.append(String.format(">%s [%s](%s/-/jobs/%s) : <font color='%s'>%s</font> %s %s %ss%n%n", emoji, build.getStage(), project.getWebUrl(), build.getId(), color, build.getStatus(), fileName, enableEmoji() ? "\uD83D\uDD57" : "", costTime));
            }
        } else {
            String pipelineCancelDeleteUrl = GitlabEndpoint.PIPELINE_CANCEL_DELETE_URL;
            String localhostIp = SpringEnvHelper.getLocalhostIp();
            Long projectId = project.getId();
            Long pipelineId = objectAttributes.getId();
            Integer port = SpringEnvHelper.getPort();
            String hostSchema = String.format("http://%s:%s/%s", localhostIp, port, webhook().getId());
            sb.append(String.format("[%s取消运行](%s%s?projectId=%s&pipelineId=%s&action=cancel) ",
                    enableEmoji() ? "\uD83D\uDEAB" : "", hostSchema, pipelineCancelDeleteUrl, projectId, pipelineId));
            sb.append(String.format("[%s重新运行](%s%s?projectId=%s&pipelineId=%s&action=retry) ",
                    enableEmoji() ? "♻️" : "", hostSchema, pipelineCancelDeleteUrl, projectId, pipelineId));
            sb.append(String.format("[%s删除](%s%s?projectId=%s&pipelineId=%s&action=delete) %n%n",
                    enableEmoji() ? "⛔" : "", hostSchema, pipelineCancelDeleteUrl, projectId, pipelineId));

        }
        return sb.toString();
    }


    @Override
    public List<String> notifies() {
        return Collections.singletonList(String.valueOf(pipelineHook.getUser().getId()));
    }

}
