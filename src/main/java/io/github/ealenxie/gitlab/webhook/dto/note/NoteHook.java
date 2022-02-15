package io.github.ealenxie.gitlab.webhook.dto.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/1/21 15:51
 */
@Setter
@Getter
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

    @Setter
    @Getter
    public static class ObjectAttributes {
        private String attachment;
        @JsonProperty("author_id")
        private Long authorId;
        @JsonProperty("change_position")
        private String changePosition;
        @JsonProperty("commit_id")
        private String commitId;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("discussion_id")
        private String discussionId;
        private Long id;
        @JsonProperty("line_code")
        private String lineCode;
        private String note;
        @JsonProperty("noteable_id")
        private Long noteAbleId;
        @JsonProperty("noteable_type")
        private String noteAbleType;
        @JsonProperty("original_position")
        private String originalPosition;
        private String position;
        private Long projectId;
        @JsonProperty("resolved_at")
        private String resolvedAt;
        @JsonProperty("resolved_by_id")
        private String resolvedById;
        @JsonProperty("resolved_by_push")
        private String resolvedByPush;
        @JsonProperty("st_diff")
        private String stDiff;
        private Boolean system;
        private String type;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("updated_by_id")
        private Long updatedById;
        private String description;
        private String url;
    }
}
