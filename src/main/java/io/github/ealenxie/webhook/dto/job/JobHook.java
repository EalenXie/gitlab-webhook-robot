package io.github.ealenxie.webhook.dto.job;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;

import java.util.Objects;

/**
 * Created by EalenXie on 2022/1/24 13:14
 */
public class JobHook implements DingRobotActionCard, DingRobotMarkdown {

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

    public String getObjectKind() {
        return objectKind;
    }

    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public String getBeforeSha() {
        return beforeSha;
    }

    public void setBeforeSha(String beforeSha) {
        this.beforeSha = beforeSha;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Long getBuildId() {
        return buildId;
    }

    public void setBuildId(Long buildId) {
        this.buildId = buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildStage() {
        return buildStage;
    }

    public void setBuildStage(String buildStage) {
        this.buildStage = buildStage;
    }

    public String getBuildCreatedAt() {
        return buildCreatedAt;
    }

    public void setBuildCreatedAt(String buildCreatedAt) {
        this.buildCreatedAt = buildCreatedAt;
    }

    public String getBuildStartedAt() {
        return buildStartedAt;
    }

    public void setBuildStartedAt(String buildStartedAt) {
        this.buildStartedAt = buildStartedAt;
    }

    public String getBuildFinishedAt() {
        return buildFinishedAt;
    }

    public void setBuildFinishedAt(String buildFinishedAt) {
        this.buildFinishedAt = buildFinishedAt;
    }

    public Double getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDuration(Double buildDuration) {
        this.buildDuration = buildDuration;
    }

    public Double getBuildQueuedDuration() {
        return buildQueuedDuration;
    }

    public void setBuildQueuedDuration(Double buildQueuedDuration) {
        this.buildQueuedDuration = buildQueuedDuration;
    }

    public Boolean getBuildAllowFailure() {
        return buildAllowFailure;
    }

    public void setBuildAllowFailure(Boolean buildAllowFailure) {
        this.buildAllowFailure = buildAllowFailure;
    }

    public String getBuildFailureReason() {
        return buildFailureReason;
    }

    public void setBuildFailureReason(String buildFailureReason) {
        this.buildFailureReason = buildFailureReason;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getText() {
        String project = String.format("[[%s]](%s)", repository.getName(), repository.getHomepage());
        String pipeline = String.format("pipeline[#%s](%s/-/pipelines/%s)", pipelineId, repository.getHomepage(), pipelineId);
        String costTime = String.format("%.0f", getBuildDuration());
        if (costTime.equals("")) {
            costTime = "0";
        }
        Emoji emoji = new Emoji("");
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
        return String.format("<font color='#000000'>%s %s %s %s%ss</font>", project, pipeline, build,new Emoji("\uD83D\uDD57"), costTime);
    }
}
