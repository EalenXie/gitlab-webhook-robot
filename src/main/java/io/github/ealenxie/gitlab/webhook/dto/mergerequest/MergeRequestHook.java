package io.github.ealenxie.gitlab.webhook.dto.mergerequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by EalenXie on 2021/12/10 10:12
 */
@Getter
@Setter
public class MergeRequestHook implements MarkDownMsg {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private User user;
    private Project project;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    private String[] labels;
    private Changes changes;
    private Repository repository;

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getMarkdown() {
        StringBuilder sb = new StringBuilder();
        String p = String.format("[[%s]](%s)", project.getName(), project.getWebUrl());
        String sources = String.format("[%s](%s/-/tree/%s)", objectAttributes.getSourceBranch(), project.getWebUrl(), objectAttributes.getSourceBranch());
        String targets = String.format("[%s](%s/-/tree/%s)", objectAttributes.getTargetBranch(), project.getWebUrl(), objectAttributes.getTargetBranch());
        String u = String.format("[%s](%s)", user.getUsername(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()));
        String merge = String.format(" [#%s](%s)(%s)", objectAttributes.getId(), objectAttributes.getUrl(), objectAttributes.getTitle());
        sb.append(String.format("<font color='#000000'>%s %s %s %s %s </font>%n%n", p, u, objectAttributes.getState(), objectKind, merge));
        switch (objectAttributes.getState()) {
            case "opened":
                sb.append(String.format("%s %s  wants to merge %s ➔➔ %s %n", new Emoji(" \uD83D\uDE00 "), user.getUsername(), sources, targets));
                String c = String.format(" %s - %s%n", objectAttributes.getLastCommit().getAuthor().getName(), objectAttributes.getLastCommit().getMessage());
                sb.append(String.format(">[%s](%s)%s", objectAttributes.getLastCommit().getId().substring(0, 8), objectAttributes.getLastCommit().getUrl(), c));
                break;
            case "merged":
                sb.append(String.format(" %s %s has completed the merge %s➔➔%s%s%n", new Emoji(" \uD83D\uDE00 "), user.getUsername(), sources, targets, new Emoji("✔️")));
                break;
            case "closed":
                sb.append(String.format(" %s %s has closed the merge %s➔➔%s%s %n", new Emoji(" \uD83D\uDE36 "), user.getUsername(), sources, targets, new Emoji("\uD83D\uDEAB")));
                break;
            default:
                break;
        }
        return sb.toString();
    }

    @Setter
    @Getter
    public static class ObjectAttributes {
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
    }
}
