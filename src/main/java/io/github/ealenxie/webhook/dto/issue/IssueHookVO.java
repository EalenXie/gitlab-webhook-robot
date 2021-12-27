package io.github.ealenxie.webhook.dto.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/16 14:15
 */
public class IssueHookVO implements HookVO, DingRobotActionCard {

    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private UserVO user;
    private ProjectVO project;
    @JsonProperty("object_attributes")
    private ObjectAttributesVO objectAttributes;
    private List<LabelVO> labels;
    private ChangesVO changes;
    private RepositoryVO repository;

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

    public List<LabelVO> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelVO> labels) {
        this.labels = labels;
    }

    public ChangesVO getChanges() {
        return changes;
    }

    public void setChanges(ChangesVO changes) {
        this.changes = changes;
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
        StringBuilder sb = new StringBuilder();
        String projectUrl = "[" + getProject().getName() + "](" + project.getWebUrl() + ")";
        String issue = "[#" + objectAttributes.getId() + "](" + objectAttributes.getUrl() + ")";
        String emoji = "";
        String titleEmoji = "";
        if (objectAttributes.getState().equals("opened")) {
            titleEmoji = "\uD83D\uDD34";
            emoji = "\uD83D\uDE4B\u200D♂️";
        } else if (objectAttributes.getState().equals("closed")) {
            titleEmoji = "\uD83D\uDFE2";
            emoji = "✌️";
        }
        sb.append("#### ").append(titleEmoji).append(projectUrl).append(" **").append(objectAttributes.getTitle()).append("** \n");
        sb.append("<font color='#000000' >").append("The Issue [").append(issue).append("] ").append(objectAttributes.getState()).append(emoji).append(" by [").append(user.getUsername()).append("](").append(getUserHomePage(project.getWebUrl(), user.getUsername())).append(") </font> \n");
        sb.append(">").append(objectAttributes.getDescription());
        return sb.toString();
    }

}


