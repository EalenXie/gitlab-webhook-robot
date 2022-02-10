package io.github.ealenxie.gitlab.webhook.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;

/**
 * Created by EalenXie on 2022/1/21 15:51
 */
public class NoteHook implements MarkDownMsg {

    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private User user;
    @JsonProperty("project_id")
    private Long projectId;
    private Project project;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    private Repository repository;
    private Issue issue;

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


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }


    public ObjectAttributes getObjectAttributes() {
        return objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributes objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getMarkdown() {
        StringBuilder sb = new StringBuilder();
        String u = String.format("[%s](%s)", user.getUsername(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()));
        String i = String.format("[#%s](%s)", issue.getId(), issue.getUrl());
        String n = String.format("[%s](%s)", objectKind, objectAttributes.getUrl());
        sb.append(String.format("<font color='#000000'>%s%s add new %s in Issue[%s]</font>%n%n", u, new Emoji("\uD83E\uDDD0"), n, i));
        sb.append(String.format("**%s**%n%n>%s%n", issue.getTitle(), objectAttributes.getNote()));
        return sb.toString();
    }
}
