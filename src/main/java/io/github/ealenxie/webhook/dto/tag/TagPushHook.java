package io.github.ealenxie.webhook.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/1/22 10:07
 */
@Setter
@Getter
public class TagPushHook implements MarkDownMsg {

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
    private Project project;
    private List<Commit> commits;
    @JsonProperty("total_commits_count")
    private Integer totalCommitsCount;
    private Repository repository;

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @Override
    public String getMarkdown() {
        String[] refSplit = ref.split("/");
        String tag = refSplit[refSplit.length - 1];
        String t = String.format("[%s](%s/-/tree/%s)", tag, project.getWebUrl(), tag);
        String p = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String user = String.format("[%s](%s)", userUsername, UserUtils.getUserHomePage(project.getWebUrl(), userUsername));
        return String.format("%s push new tag(%s) by %s %s%n%n > %s", p, t, user, new Emoji("\uD83D\uDE80\uD83D\uDE80\uD83D\uDE80"), message);
    }
}
