package io.github.ealenxie.webhook.dto.mergerequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;

/**
 * Created by EalenXie on 2021/12/10 10:12
 */
public class MergeRequestHookVO implements HookVO, DingRobotActionCard {

    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private UserVO user;
    private ProjectVO project;
    @JsonProperty("object_attributes")
    private ObjectAttributesVO objectAttributes;
    private String[] labels;
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

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
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
        sb.append("<font color='#000000'>");
        sb.append("[[").append(project.getName()).append("]](").append(project.getWebUrl()).append(") ");
        String source = "[" + objectAttributes.getSourceBranch() + "](" + project.getWebUrl() + "/-/tree/" + objectAttributes.getSourceBranch() + ")";
        String target = "[" + objectAttributes.getTargetBranch() + "](" + project.getWebUrl() + "/-/tree/" + objectAttributes.getTargetBranch() + ")";
        sb.append("[").append(user.getUsername()).append("](").append(getUserHomePage(project.getWebUrl(), user.getUsername())).append(") ").append(objectAttributes.getState()).append(" ").append(objectKind).append(" [#").append(objectAttributes.getId()).append("](").append(objectAttributes.getUrl()).append(")(").append(objectAttributes.getTitle()).append(")</font>\n\n");
        switch (objectAttributes.getState()) {
            case "opened":
                sb.append(" \uD83D\uDE00 ").append(user.getUsername()).append(" wants to merge  ").append(source).append("➔➔").append(target).append("\n");
                sb.append(">[").append(objectAttributes.getLastCommit().getId(), 0, 8).append("]").append("(").append(objectAttributes.getLastCommit().getUrl()).append(")").append(" ").append(objectAttributes.getLastCommit().getAuthor().getName()).append(" - ").append(objectAttributes.getLastCommit().getMessage()).append("\n");
                break;
            case "merged":
                sb.append(" \uD83D\uDE00 ").append(user.getUsername()).append(" has completed the merge ").append(source).append("➔➔").append(target).append("✔️\n");
                break;
            case "closed":
                sb.append(" \uD83D\uDE36 ").append(user.getUsername()).append(" has closed the merge ").append(source).append("➔➔").append(target).append("\uD83D\uDEAB \n");
                break;
            default:
                break;
        }
        return sb.toString();
    }
}
