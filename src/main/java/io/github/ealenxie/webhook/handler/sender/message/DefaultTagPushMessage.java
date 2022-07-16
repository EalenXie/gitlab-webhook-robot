package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.Project;
import io.github.ealenxie.webhook.dto.UserUtils;
import io.github.ealenxie.webhook.dto.tag.TagPushHook;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultTagPushMessage extends WebhookMessage {
    private final TagPushHook tagPushHook;

    public DefaultTagPushMessage(WebhookDefinition webhook, TagPushHook tagPushHook) {
        super(webhook);
        this.tagPushHook = tagPushHook;
    }

    @Override
    public String title() {
        return tagPushHook.getObjectKind();
    }

    @Override
    public String message() {
        Project project = tagPushHook.getProject();
        String userUsername = tagPushHook.getUserUsername();
        String[] refSplit = tagPushHook.getRef().split("/");
        String tag = refSplit[refSplit.length - 1];
        String t = String.format("[%s](%s/-/tree/%s)", tag, project.getWebUrl(), tag);
        String p = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String user = String.format("[%s](%s)", userUsername, UserUtils.getUserHomePage(project.getWebUrl(), userUsername));
        return String.format("%s push new tag(%s) by %s %s%n%n > %s", p, t, user, enableEmoji() ? "\uD83D\uDE80\uD83D\uDE80\uD83D\uDE80" : "", tagPushHook.getMessage());
    }

    @Override
    public List<String> notifies() {
        return Collections.singletonList(String.valueOf(tagPushHook.getUserId()));
    }
}
