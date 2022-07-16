package io.github.ealenxie.webhook.handler.sender.message;

import io.github.ealenxie.webhook.dto.Project;
import io.github.ealenxie.webhook.dto.release.ReleaseHook;
import io.github.ealenxie.webhook.meta.WebhookDefinition;

import java.util.Collections;
import java.util.List;

/**
 * Created by EalenXie on 2022/7/10 15:58
 */
public class DefaultReleaseMessage extends WebhookMessage{

    private final ReleaseHook releaseHook;

    public DefaultReleaseMessage(WebhookDefinition webhook,ReleaseHook releaseHook) {
        super(webhook);
        this.releaseHook = releaseHook;
    }

    @Override
    public String title() {
        return releaseHook.getObjectKind();
    }

    @Override
    public String message() {
        String tag =releaseHook. getTag();
        Project project = releaseHook.getProject();
        ReleaseHook.Assets assets = releaseHook.getAssets();
        String tags = String.format("[%s](%s/-/tags/%s)", tag, project.getWebUrl(), tag);
        String head = String.format("<font color='#000000'>[%s](%s) %s new %s %s by tag%s(%s)</font> %s%n%n",
                project.getName(), project.getWebUrl(),
                releaseHook.getAction(), releaseHook.getObjectKind(),
                String.format("[%s](%s)",releaseHook. getName(), releaseHook.getUrl()),
                enableEmoji() ? "\uD83D\uDCCC" : "", tags,
                enableEmoji() ? "\uD83D\uDE80\uD83D\uDE80\uD83D\uDE80" : "");
        StringBuilder context = new StringBuilder(head);
        context.append(releaseHook.getDescription()).append("\n\n");
        context.append("<font color='#000000'>Assets</font> \n");
        for (ReleaseHook.Assets.Source source : assets.getSources()) {
            context.append(String.format("> - [%s Source code (%s)](%s) %n",
                    enableEmoji() ? "\uD83D\uDCC1" : "",
                    source.getFormat(), source.getUrl()));
        }
        return context.toString();
    }


    @Override
    public List<String> notifies() {
        return Collections.singletonList(releaseHook.getCreateAt());
    }
}
