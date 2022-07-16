package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.Commit;
import io.github.ealenxie.webhook.dto.Project;
import io.github.ealenxie.webhook.dto.UserUtils;
import io.github.ealenxie.webhook.dto.push.PushHook;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultPushMessage extends WebhookMessage {
    private final PushHook pushHook;

    public DefaultPushMessage(WebhookDefinition webhook, PushHook pushHook) {
        super(webhook);
        this.pushHook = pushHook;
    }


    @Override
    public String title() {
        return pushHook.getObjectKind();
    }

    @Override
    public String message() {
        List<Commit> commits = pushHook.getCommits();
        Project project = pushHook.getProject();
        String userUsername = pushHook.getUserUsername();
        Collections.sort(commits);
        StringBuilder sb = new StringBuilder();
        String[] refSplit = pushHook.getRef().split("/");
        String branch = refSplit[refSplit.length - 1];
        sb.append(String.format("[[%s:%s]](%s/-/tree/%s) ", project.getName(), branch, project.getWebUrl(), branch));
        String c = commits.size() > 1 ? "commits" : "commit";
        String user = userUsername == null ? pushHook.getUserName() : String.format("[%s](%s)", userUsername, UserUtils.getUserHomePage(project.getWebUrl(), userUsername));
        sb.append(String.format("<font color='#000000'>%s %s new %s by %s %s </font>%n%n", pushHook.getEventName(), pushHook.getTotalCommitsCount(), c, enableEmoji() ? "\uD83D\uDE00" : "", user));
        for (Commit vo : commits) {
            sb.append(String.format("> [%s](%s) %s - %s%n%n", vo.getId().substring(0, 8), vo.getUrl(), vo.getAuthor().getName(), vo.getTitle()));
        }
        return sb.toString();
    }

    @Override
    public List<String> notifies() {
        return Collections.singletonList(String.valueOf(pushHook.getUserId()));
    }
}
