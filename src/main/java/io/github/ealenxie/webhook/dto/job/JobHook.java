package io.github.ealenxie.webhook.dto.job;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by EalenXie on 2022/1/24 13:14
 */
@Getter
@Setter
public class JobHook implements MarkDownMsg {
    @JsonProperty("object_kind")
    private String objectKind;
    private String ref;
    private Boolean tag;
    @JsonProperty("before_sha")
    private String beforeSha;
    private String sha;
    @JsonProperty("build_id")
    private Long buildId;
    @JsonProperty("build_name")
    private String buildName;
    @JsonProperty("build_stage")
    private String buildStage;
    @JsonProperty("build_status")
    private String buildStatus;
    @JsonProperty("build_created_at")
    private String buildCreatedAt;
    @JsonProperty("build_started_at")
    private String buildStartedAt;
    @JsonProperty("build_finished_at")
    private String buildFinishedAt;
    @JsonProperty("build_duration")
    private Double buildDuration;
    @JsonProperty("build_queued_duration")
    private Double buildQueuedDuration;
    @JsonProperty("build_allow_failure")
    private Boolean buildAllowFailure;
    @JsonProperty("build_failure_reason")
    private String buildFailureReason;
    @JsonProperty("pipeline_id")
    private Long pipelineId;
    private Runner runner;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("project_name")
    private String projectName;
    private User user;
    private Commit commit;
    private Repository repository;

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getMarkdown() {
        String project = String.format("[[%s]](%s)", repository.getName(), repository.getHomepage());
        String pipeline = String.format("pipeline[#%s](%s/-/pipelines/%s)", pipelineId, repository.getHomepage(), pipelineId);
        String costTime = String.format("%.0f", getBuildDuration());
        if (costTime.equals("")) {
            costTime = "0";
        }
        Emoji emoji = new Emoji();
        String color = "#000000";
        if (Objects.equals(buildStatus, "success")) {
            color = "#00b140";
            emoji.setCode("✔️");
        } else if (Objects.equals(buildStatus, "failed")) {
            color = "#ff0000";
            emoji.setCode("❌");
        } else if (Objects.equals(buildStatus, "canceled")) {
            color = "#FFDAC8";
            emoji.setCode("⏹️");
        } else if (Objects.equals(buildStatus, "skipped")) {
            color = "#8E8E8E";
            emoji.setCode("⏭️");
        }
        String build = String.format("<font color='%s'> [%s](%s/-/jobs/%s) %s%s</font>", color, buildStage, repository.getHomepage(), buildId, buildStatus, emoji);
        return String.format("<font color='#000000'>%s %s %s %s%ss</font>", project, pipeline, build, new Emoji("\uD83D\uDD57"), costTime);
    }

    @Getter
    @Setter
    public static class Commit {
        private Integer id;
        private String sha;
        private String message;
        @JsonProperty("author_name")
        private String authorName;
        @JsonProperty("author_email")
        private String authorEmail;
        @JsonProperty("author_url")
        private String authorUrl;
        private String status;
        private Integer duration;
        @JsonProperty("started_at")
        private String startedAt;
        @JsonProperty("finished_at")
        private String finishedAt;
    }

}
