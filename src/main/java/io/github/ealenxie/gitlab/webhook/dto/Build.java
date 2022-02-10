package io.github.ealenxie.gitlab.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by EalenXie on 2021/12/1 9:46
 */
public class Build implements Comparable<Build> {

    private Long id;
    private String stage;
    private String name;
    private String status;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("finished_at")
    private String finishedAt;
    private Double duration;
    @JsonProperty("queued_duration")
    private Double queuedDuration;
    private String when;
    private Boolean manual;
    @JsonProperty("allow_failure")
    private Boolean allowFailure;
    private User user;
    private Runner runner;
    @JsonProperty("artifacts_file")
    private ArtifactsFile artifactFile;
    private String environment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getQueuedDuration() {
        return queuedDuration;
    }

    public void setQueuedDuration(Double queuedDuration) {
        this.queuedDuration = queuedDuration;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Boolean getAllowFailure() {
        return allowFailure;
    }

    public void setAllowFailure(Boolean allowFailure) {
        this.allowFailure = allowFailure;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public ArtifactsFile getArtifactFile() {
        return artifactFile;
    }

    public void setArtifactFile(ArtifactsFile artifactFile) {
        this.artifactFile = artifactFile;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public int compareTo(Build o) {
        if (Objects.equals(id, o.id)) {
            return 0;
        }
        if (id > o.id) return 1;
        else return -1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
