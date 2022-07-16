package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.Emoji;
import io.github.ealenxie.webhook.dto.Project;
import io.github.ealenxie.webhook.dto.User;
import io.github.ealenxie.webhook.dto.UserUtils;
import io.github.ealenxie.webhook.dto.mergerequest.MergeRequestHook;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultMergeRequestMessage extends WebhookMessage {
    private final MergeRequestHook mergeRequestHook;

    public DefaultMergeRequestMessage(WebhookDefinition webhook, MergeRequestHook mergeRequestHook) {
        super(webhook);
        this.mergeRequestHook = mergeRequestHook;
    }

    @Override
    public String title() {
        return mergeRequestHook.getObjectKind();
    }

    @Override
    public String message() {
        User user = mergeRequestHook.getUser();
        Project project = mergeRequestHook.getProject();
        MergeRequestHook.ObjectAttributes objectAttributes = mergeRequestHook.getObjectAttributes();
        StringBuilder sb = new StringBuilder();
        String p = String.format("[[%s]](%s)", project.getName(), project.getWebUrl());
        String sources = String.format("[%s](%s/-/tree/%s)", objectAttributes.getSourceBranch(), project.getWebUrl(), objectAttributes.getSourceBranch());
        String targets = String.format("[%s](%s/-/tree/%s)", objectAttributes.getTargetBranch(), project.getWebUrl(), objectAttributes.getTargetBranch());
        String u = String.format("[%s](%s)", user.getUsername(), UserUtils.getUserHomePage(project.getWebUrl(), user.getUsername()));
        String merge = String.format(" [#%s](%s)(%s)", objectAttributes.getId(), objectAttributes.getUrl(), objectAttributes.getTitle());
        sb.append(String.format("<font color='#000000'>%s %s %s %s %s </font>%n%n", p, u, objectAttributes.getState(), mergeRequestHook.getObjectKind(), merge));
        switch (objectAttributes.getState()) {
            case "opened":
                sb.append(String.format("%s %s  wants to merge %s ➔➔ %s %n",
                        enableEmoji() ? " \uD83D\uDE00 " : "", user.getUsername(), sources, targets));
                String c = String.format(" %s - %s%n",
                        objectAttributes.getLastCommit().getAuthor().getName(), objectAttributes.getLastCommit().getMessage());
                sb.append(String.format(">[%s](%s)%s",
                        objectAttributes.getLastCommit().getId().substring(0, 8), objectAttributes.getLastCommit().getUrl(), c));
                break;
            case "merged":
                sb.append(String.format(" %s %s has completed the merge %s➔➔%s%s%n", enableEmoji() ? " \uD83D\uDE00 " : "", user.getUsername(), sources, targets, new Emoji("✔️")));
                break;
            case "closed":
                sb.append(String.format(" %s %s has closed the merge %s➔➔%s%s %n", enableEmoji() ? " \uD83D\uDE36 " : "", user.getUsername(), sources, targets, new Emoji("\uD83D\uDEAB")));
                break;
            default:
                break;
        }
        return sb.toString();
    }


    @Override
    public List<String> notifies() {
        return Collections.singletonList(String.valueOf(mergeRequestHook.getUser().getId()));
    }
}
