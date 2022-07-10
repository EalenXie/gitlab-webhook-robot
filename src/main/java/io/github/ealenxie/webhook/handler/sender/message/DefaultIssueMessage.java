package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.Project;
import io.github.ealenxie.webhook.dto.User;
import io.github.ealenxie.webhook.dto.UserUtils;
import io.github.ealenxie.webhook.dto.issue.IssueHook;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultIssueMessage extends IssueHook implements EmojiSupport, EventMessage {
    @Override
    public String title() {
        return getObjectKind();
    }

    @Override
    public String message() {
        ObjectAttributes objectAttributes = getObjectAttributes();
        Project project = getProject();
        User user = getUser();
        StringBuilder sb = new StringBuilder();
        String projectUrl = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String issue = String.format("[#%s](%s)", getObjectAttributes().getId(), objectAttributes.getUrl());
        String titleEmoji = "";
        String statusEmoji = "";
        if (enableEmoji()) {
            if (getObjectAttributes().getState().equals("opened")) {
                titleEmoji = "\uD83D\uDD34";
                statusEmoji = "\uD83D\uDE4B\u200D♂️";
            } else if (objectAttributes.getState().equals("closed")) {
                titleEmoji = "\uD83D\uDFE2";
                statusEmoji = "✌️";
            }
        }
        sb.append(String.format("#### %s%s **%s** %n", titleEmoji, projectUrl, objectAttributes.getTitle()));
        sb.append(String.format("<font color='#000000'>The Issue [%s] %s%s by [%s](%s) </font> %n>%s", issue,
                objectAttributes.getState(), statusEmoji,
                user.getUsername(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()),
                objectAttributes.getDescription()));
        return sb.toString();
    }


    @Override
    public List<String> notifies() {
        return Collections.singletonList(String.valueOf(getUser().getId()));
    }
}
