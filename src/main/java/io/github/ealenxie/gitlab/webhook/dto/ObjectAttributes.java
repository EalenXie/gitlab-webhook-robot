package io.github.ealenxie.gitlab.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by EalenXie on 2021/12/1 9:25
 */
public class ObjectAttributes {

    private Long id;

    private String ref;

    private Boolean tag;

    private String sha;

    @JsonProperty("before_sha")
    private String beforeSha;

    private String source;

    private String status;

    @JsonProperty("detailed_status")
    private String detailedStatus;

    private String[] stages;

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("finished_at")
    private String finishedAt;

    private Double duration;

    @JsonProperty("queued_duration")
    private Double queuedDuration;

    private String[] variables;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getBeforeSha() {
        return beforeSha;
    }

    public void setBeforeSha(String beforeSha) {
        this.beforeSha = beforeSha;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetailedStatus() {
        return detailedStatus;
    }

    public void setDetailedStatus(String detailedStatus) {
        this.detailedStatus = detailedStatus;
    }

    public String[] getStages() {
        return stages;
    }

    public void setStages(String[] stages) {
        this.stages = stages;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String[] getVariables() {
        return variables;
    }

    public void setVariables(String[] variables) {
        this.variables = variables;
    }
}
