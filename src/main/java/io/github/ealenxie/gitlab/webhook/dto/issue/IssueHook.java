package io.github.ealenxie.gitlab.webhook.dto.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/16 14:15
 */
public class IssueHook implements MarkDownMsg {

    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private User user;
    private Project project;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    private List<Label> labels;
    private Changes changes;
    private Repository repository;

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

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Changes getChanges() {
        return changes;
    }

    public void setChanges(Changes changes) {
        this.changes = changes;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getMarkdown() {
        StringBuilder sb = new StringBuilder();
        String projectUrl = String.format("[%s](%s)", getProject().getName(), project.getWebUrl());
        String issue = String.format("[#%s](%s)", objectAttributes.getId(), objectAttributes.getUrl());
        Emoji emoji = new Emoji();
        Emoji titleEmoji = new Emoji();
        if (objectAttributes.getState().equals("opened")) {
            titleEmoji.setCode("\uD83D\uDD34");
            emoji.setCode("\uD83D\uDE4B\u200D♂️");
        } else if (objectAttributes.getState().equals("closed")) {
            titleEmoji.setCode("\uD83D\uDFE2");
            emoji.setCode("✌️");
        }
        sb.append(String.format("#### %s%s **%s** %n", titleEmoji, projectUrl, objectAttributes.getTitle()));
        sb.append(String.format("<font color='#000000'>The Issue [%s] %s%s by [%s](%s) </font> %n>%s", issue, objectAttributes.getState(), emoji, user.getUsername(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()), objectAttributes.getDescription()));
        return sb.toString();
    }

}


