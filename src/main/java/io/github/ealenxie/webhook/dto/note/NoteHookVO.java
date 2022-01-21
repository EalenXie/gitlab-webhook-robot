package io.github.ealenxie.webhook.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;

/**
 * Created by EalenXie on 2022/1/21 15:51
 */
public class NoteHookVO implements DingRobotActionCard {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private UserVO user;
    @JsonProperty("project_id")
    private Long projectId;
    private ProjectVO project;
    @JsonProperty("object_attributes")
    private ObjectAttributesVO objectAttributes;
    private RepositoryVO repository;
    private IssueVO issue;

    public String getObjectKind() {
        return objectKind;
    }

    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
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

    public ObjectAttributesVO getObjectAttributes() {
        return objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributesVO objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    public RepositoryVO getRepository() {
        return repository;
    }

    public void setRepository(RepositoryVO repository) {
        this.repository = repository;
    }

    public IssueVO getIssue() {
        return issue;
    }

    public void setIssue(IssueVO issue) {
        this.issue = issue;
    }

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        String u = String.format("[%s](%s)", user.getUsername(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()));
        String i = String.format("[#%s](%s)", issue.getId(), issue.getUrl());
        String n = String.format("[%s](%s)", objectKind, objectAttributes.getUrl());
        sb.append(String.format("<font color='#000000'>%s\uD83E\uDDD0 add new %s in Issue[%s]</font>%n%n", u, n, i));
        sb.append(String.format("**%s**%n%n>%s%n", issue.getTitle(), objectAttributes.getNote()));
        return sb.toString();
    }
}
