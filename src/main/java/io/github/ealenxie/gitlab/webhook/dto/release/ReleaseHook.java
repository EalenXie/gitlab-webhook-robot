package io.github.ealenxie.gitlab.webhook.dto.release;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;

/**
 * Created by EalenXie on 2022/1/20 9:57
 */
public class ReleaseHook implements MarkDownMsg {


    private String id;
    @JsonProperty("created_at")
    private String createAt;
    @JsonProperty("object_kind")
    private String objectKind;
    private String name;
    private String tag;
    private String description;
    private Project project;
    private String url;
    private String action;
    private Assets assets;
    private Commit commit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getObjectKind() {
        return objectKind;
    }

    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getMarkdown() {
        String tags = String.format("[%s](%s/-/tags/%s)", tag, project.getWebUrl(), tag);
        String head = String.format("<font color='#000000'>[%s](%s) %s new %s %s by tag%s(%s)</font> %s%n%n", project.getName(), project.getWebUrl(), action, objectKind,
                String.format("[%s](%s)", name, url), new Emoji("\uD83D\uDCCC"), tags, new Emoji("\uD83D\uDE80\uD83D\uDE80\uD83D\uDE80"));
        StringBuilder context = new StringBuilder(head);
        context.append(description).append("\n\n");
        context.append("<font color='#000000'>Assets</font> \n");
        for (Source source : assets.getSources()) {
            context.append(String.format("> - [%s Source code (%s)](%s) %n", new Emoji("\uD83D\uDCC1"), source.getFormat(), source.getUrl()));
        }
        return context.toString();
    }
}
