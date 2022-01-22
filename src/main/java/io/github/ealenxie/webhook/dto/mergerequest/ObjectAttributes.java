package io.github.ealenxie.webhook.dto.mergerequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.Commit;
import io.github.ealenxie.webhook.dto.Project;

import java.util.Map;

/**
 * Created by EalenXie on 2021/12/1 9:25
 */
public class ObjectAttributes {

    @JsonProperty("assignee_id")
    private Long assigneeId;
    @JsonProperty("author_id")
    private Long authorId;
    @JsonProperty("created_at")
    private String createdAt;
    private String description;
    @JsonProperty("head_pipeline_id")
    private Long headPipelineId;
    private Long id;
    private Long iid;
    @JsonProperty("last_edited_at")
    private String lastEditedAt;
    @JsonProperty("last_edited_by_id")
    private Long lastEditedById;
    @JsonProperty("merge_commit_sha")
    private String mergeCommitSha;
    @JsonProperty("merge_error")
    private String mergeError;
    @JsonProperty("merge_params")
    private Map<String, Object> mergeParams;
    @JsonProperty("merge_status")
    private String mergeStatus;
    @JsonProperty("merge_user_id")
    private Long mergeUserId;
    @JsonProperty("merge_when_pipeline_succeeds")
    private Boolean mergeWhenPipelineSucceeds;
    @JsonProperty("milestone_id")
    private Long milestoneId;
    @JsonProperty("source_branch")
    private String sourceBranch;
    @JsonProperty("source_project_id")
    private Long sourceProjectId;
    @JsonProperty("state_id")
    private Long stateId;
    @JsonProperty("target_branch")
    private String targetBranch;
    @JsonProperty("target_project_id")
    private String targetProjectId;
    @JsonProperty("time_estimate")
    private Integer timeEstimate;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("updated_by_id")
    private Long updatedById;
    private String url;
    private Project source;
    private Project target;
    @JsonProperty("last_commit")
    private Commit lastCommit;
    @JsonProperty("work_in_progress")
    private Boolean workInProgress;
    @JsonProperty("total_time_spent")
    private Integer totalTimeSpent;
    @JsonProperty("time_change")
    private Integer timeChange;
    @JsonProperty("human_total_time_spent")
    private Integer humanTotalTimeSpent;
    @JsonProperty("human_time_change")
    private Integer humanTimeChange;
    @JsonProperty("human_time_estimate")
    private Integer humanTimeEstimate;
    @JsonProperty("assignee_ids")
    private Integer[] assigneeIds;
    private String state;
    private String action;

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHeadPipelineId() {
        return headPipelineId;
    }

    public void setHeadPipelineId(Long headPipelineId) {
        this.headPipelineId = headPipelineId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(String lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }

    public Long getLastEditedById() {
        return lastEditedById;
    }

    public void setLastEditedById(Long lastEditedById) {
        this.lastEditedById = lastEditedById;
    }

    public String getMergeCommitSha() {
        return mergeCommitSha;
    }

    public void setMergeCommitSha(String mergeCommitSha) {
        this.mergeCommitSha = mergeCommitSha;
    }

    public String getMergeError() {
        return mergeError;
    }

    public void setMergeError(String mergeError) {
        this.mergeError = mergeError;
    }

    public Map<String, Object> getMergeParams() {
        return mergeParams;
    }

    public void setMergeParams(Map<String, Object> mergeParams) {
        this.mergeParams = mergeParams;
    }

    public String getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(String mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    public Long getMergeUserId() {
        return mergeUserId;
    }

    public void setMergeUserId(Long mergeUserId) {
        this.mergeUserId = mergeUserId;
    }

    public Boolean getMergeWhenPipelineSucceeds() {
        return mergeWhenPipelineSucceeds;
    }

    public void setMergeWhenPipelineSucceeds(Boolean mergeWhenPipelineSucceeds) {
        this.mergeWhenPipelineSucceeds = mergeWhenPipelineSucceeds;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getSourceBranch() {
        return sourceBranch;
    }

    public void setSourceBranch(String sourceBranch) {
        this.sourceBranch = sourceBranch;
    }

    public Long getSourceProjectId() {
        return sourceProjectId;
    }

    public void setSourceProjectId(Long sourceProjectId) {
        this.sourceProjectId = sourceProjectId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getTargetBranch() {
        return targetBranch;
    }

    public void setTargetBranch(String targetBranch) {
        this.targetBranch = targetBranch;
    }

    public String getTargetProjectId() {
        return targetProjectId;
    }

    public void setTargetProjectId(String targetProjectId) {
        this.targetProjectId = targetProjectId;
    }

    public Integer getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Integer timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Project getSource() {
        return source;
    }

    public void setSource(Project source) {
        this.source = source;
    }

    public Project getTarget() {
        return target;
    }

    public void setTarget(Project target) {
        this.target = target;
    }

    public Commit getLastCommit() {
        return lastCommit;
    }

    public void setLastCommit(Commit lastCommit) {
        this.lastCommit = lastCommit;
    }

    public Boolean getWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(Boolean workInProgress) {
        this.workInProgress = workInProgress;
    }

    public Integer getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Integer totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public Integer getTimeChange() {
        return timeChange;
    }

    public void setTimeChange(Integer timeChange) {
        this.timeChange = timeChange;
    }

    public Integer getHumanTotalTimeSpent() {
        return humanTotalTimeSpent;
    }

    public void setHumanTotalTimeSpent(Integer humanTotalTimeSpent) {
        this.humanTotalTimeSpent = humanTotalTimeSpent;
    }

    public Integer getHumanTimeChange() {
        return humanTimeChange;
    }

    public void setHumanTimeChange(Integer humanTimeChange) {
        this.humanTimeChange = humanTimeChange;
    }

    public Integer getHumanTimeEstimate() {
        return humanTimeEstimate;
    }

    public void setHumanTimeEstimate(Integer humanTimeEstimate) {
        this.humanTimeEstimate = humanTimeEstimate;
    }

    public Integer[] getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(Integer[] assigneeIds) {
        this.assigneeIds = assigneeIds;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
