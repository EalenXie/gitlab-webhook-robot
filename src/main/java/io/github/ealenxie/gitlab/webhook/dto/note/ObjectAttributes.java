package io.github.ealenxie.gitlab.webhook.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2021/12/1 9:25
 */
public class ObjectAttributes {

    private String attachment;
    @JsonProperty("author_id")
    private Long authorId;
    @JsonProperty("change_position")
    private String changePosition;
    @JsonProperty("commit_id")
    private String commitId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("discussion_id")
    private String discussionId;
    private Long id;
    @JsonProperty("line_code")
    private String lineCode;
    private String note;
    @JsonProperty("noteable_id")
    private Long noteableId;
    @JsonProperty("noteable_type")
    private String noteableType;
    @JsonProperty("original_position")
    private String originalPosition;
    private String position;
    private Long projectId;
    @JsonProperty("resolved_at")
    private String resolvedAt;
    @JsonProperty("resolved_by_id")
    private String resolvedById;
    @JsonProperty("resolved_by_push")
    private String resolvedByPush;
    @JsonProperty("st_diff")
    private String stDiff;
    private Boolean system;
    private String type;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("updated_by_id")
    private Long updatedById;
    private String description;
    private String url;

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getChangePosition() {
        return changePosition;
    }

    public void setChangePosition(String changePosition) {
        this.changePosition = changePosition;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getNoteableId() {
        return noteableId;
    }

    public void setNoteableId(Long noteableId) {
        this.noteableId = noteableId;
    }

    public String getNoteableType() {
        return noteableType;
    }

    public void setNoteableType(String noteableType) {
        this.noteableType = noteableType;
    }

    public String getOriginalPosition() {
        return originalPosition;
    }

    public void setOriginalPosition(String originalPosition) {
        this.originalPosition = originalPosition;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(String resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public String getResolvedById() {
        return resolvedById;
    }

    public void setResolvedById(String resolvedById) {
        this.resolvedById = resolvedById;
    }

    public String getResolvedByPush() {
        return resolvedByPush;
    }

    public void setResolvedByPush(String resolvedByPush) {
        this.resolvedByPush = resolvedByPush;
    }

    public String getStDiff() {
        return stDiff;
    }

    public void setStDiff(String stDiff) {
        this.stDiff = stDiff;
    }

    public Boolean getSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
