package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.Issue;
import io.github.ealenxie.webhook.dto.Project;
import io.github.ealenxie.webhook.dto.User;
import io.github.ealenxie.webhook.dto.UserUtils;
import io.github.ealenxie.webhook.dto.note.NoteHook;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultNoteMessage extends NoteHook implements EmojiSupport, EventMessage {
    @Override
    public String title() {
        return getObjectKind();
    }

    @Override
    public String message() {
        User user = getUser();
        Project project = getProject();
        Issue issue = getIssue();
        ObjectAttributes objectAttributes = getObjectAttributes();
        StringBuilder sb = new StringBuilder();
        String u = String.format("[%s](%s)", user.getUsername(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()));
        String i = String.format("[#%s](%s)", issue.getId(), issue.getUrl());
        String n = String.format("[%s](%s)", getObjectKind(), objectAttributes.getUrl());
        sb.append(String.format("<font color='#000000'>%s%s add new %s in Issue[%s]</font>%n%n", u, enableEmoji() ? "\uD83E\uDDD0" : "", n, i));
        sb.append(String.format("**%s**%n%n>%s%n", issue.getTitle(), objectAttributes.getNote()));
        return sb.toString();
    }


    @Override
    public List<String> notifies() {
        return Collections.singletonList(String.valueOf(getUser().getId()));
    }
}
