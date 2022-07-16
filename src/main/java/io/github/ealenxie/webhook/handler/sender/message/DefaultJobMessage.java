package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.Repository;
import io.github.ealenxie.webhook.dto.job.JobHook;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultJobMessage extends WebhookMessage {

    private final JobHook jobHook;

    public DefaultJobMessage(WebhookDefinition webhook, JobHook jobHook) {
        super(webhook);
        this.jobHook = jobHook;
    }

    @Override
    public String title() {
        return jobHook.getObjectKind();
    }

    @Override
    public String message() {
        Repository repository = jobHook.getRepository();
        Long pipelineId = jobHook.getPipelineId();
        String buildStatus = jobHook.getBuildStatus();
        String project = String.format("[[%s]](%s)", repository.getName(), repository.getHomepage());
        String pipeline = String.format("pipeline[#%s](%s/-/pipelines/%s)", pipelineId, repository.getHomepage(), pipelineId);
        String costTime = String.format("%.0f", jobHook.getBuildDuration());
        if (costTime.equals("")) {
            costTime = "0";
        }
        String emoji = "";
        String color = "#000000";
        if (Objects.equals(buildStatus, "success")) {
            color = "#00b140";
            emoji = "✔️";
        } else if (Objects.equals(buildStatus, "failed")) {
            color = "#ff0000";
            emoji = "❌";
        } else if (Objects.equals(buildStatus, "canceled")) {
            color = "#FFDAC8";
            emoji = "⏹️";
        } else if (Objects.equals(buildStatus, "skipped")) {
            color = "#8E8E8E";
            emoji = "⏭️";
        }
        if (!enableEmoji()) {
            emoji = "";
        }
        String build = String.format("<font color='%s'> [%s](%s/-/jobs/%s) %s%s</font>",
                color, jobHook.getBuildStage(), repository.getHomepage(),
                jobHook.getBuildId(), buildStatus, emoji);
        return String.format("<font color='#000000'>%s %s %s %s%ss</font>", project, pipeline, build, enableEmoji() ? "\uD83D\uDD57" : "", costTime);
    }


    @Override
    public List<String> notifies() {
        return Collections.singletonList(String.valueOf(jobHook.getUser().getId()));
    }
}
