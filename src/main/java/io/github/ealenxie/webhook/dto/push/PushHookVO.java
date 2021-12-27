package io.github.ealenxie.webhook.dto.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;


import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2021/12/1 11:21
 */
public class PushHookVO implements HookVO, DingRobotActionCard {

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
        Collections.sort(commits);
        StringBuilder sb = new StringBuilder();
        String[] refSplit = ref.split("/");
        String branch = refSplit[refSplit.length - 1];
        sb.append("[[").append(project.getName()).append(":").append(branch).append("]](").append(project.getWebUrl()).append("/-/tree/").append(branch).append(") ");
        String c = commits.size() > 1 ? "commits" : "commit";
        sb.append("<font color='#000000'>");
        sb.append(eventName).append(" ").append(totalCommitsCount).append(" new ").append(c).append(" by \uD83D\uDE00 ").append("[").append(userUsername).append("](").append(getUserHomePage(project.getWebUrl(), userUsername)).append(")").append("</font>\n\n");
        for (CommitVO vo : commits) {
            sb.append("- ").append("[").append(vo.getId(), 0, 8).append("]").append("(").append(vo.getUrl()).append(")").append(" ").append(vo.getAuthor().getName()).append(" - ").append(vo.getTitle()).append("\n");
        }
        return sb.toString();
    }
}
