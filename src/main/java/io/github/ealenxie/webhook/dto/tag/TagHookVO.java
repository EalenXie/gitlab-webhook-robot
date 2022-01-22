package io.github.ealenxie.webhook.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;

import java.util.List;

/**
 * Created by EalenXie on 2022/1/22 10:07
 */
public class TagHookVO implements DingRobotActionCard {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_name")
    private String eventName;
    private String before;
    private String after;
    private String ref;
    @JsonProperty("checkout_sha")
    private String checkoutSha;
    private String message;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_username")
    private String userUsername;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("user_avatar")
    private String userAvatar;
    @JsonProperty("project_id")
    private Long projectId;
    private ProjectVO project;
    private List<CommitVO> commits;
    @JsonProperty("total_commits_count")
    private Integer totalCommitsCount;
    private RepositoryVO repository;


    public String getObjectKind() {
        return objectKind;
    }

    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCheckoutSha() {
        return checkoutSha;
    }

    public void setCheckoutSha(String checkoutSha) {
        this.checkoutSha = checkoutSha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public ProjectVO getProject() {
        return project;
    }

    public void setProject(ProjectVO project) {
        this.project = project;
    }

    public List<CommitVO> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitVO> commits) {
        this.commits = commits;
    }

    public Integer getTotalCommitsCount() {
        return totalCommitsCount;
    }

    public void setTotalCommitsCount(Integer totalCommitsCount) {
        this.totalCommitsCount = totalCommitsCount;
    }

    public RepositoryVO getRepository() {
        return repository;
    }

    public void setRepository(RepositoryVO repository) {
        this.repository = repository;
    }

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getText() {
        String[] refSplit = ref.split("/");
        String tag = refSplit[refSplit.length - 1];
        String t = String.format("[%s](%s/-/tree/%s)", tag, project.getWebUrl(),tag);
        String p = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String user = String.format("[%s](%s)", userUsername, UserUtils.getUserHomePage(project.getWebUrl(), userUsername));
        return String.format("%s push new tag(%s) by %s \uD83D\uDE80\uD83D\uDE80\uD83D\uDE80%n%n > %s", p, t, user, message);
    }
}
