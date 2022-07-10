package io.github.ealenxie.webhook.dto.release;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.Commit;
import io.github.ealenxie.webhook.dto.Emoji;
import io.github.ealenxie.webhook.dto.MarkDownMsg;
import io.github.ealenxie.webhook.dto.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/1/20 9:57
 */
@Setter
@Getter
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

    @Setter
    @Getter
    public static class Assets {
        private Integer count;
        private String[] links;
        private List<Source> sources;

        @Getter
        @Setter
        public static class Source {
            private String format;
            private String url;
        }
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
        for (Assets.Source source : assets.getSources()) {
            context.append(String.format("> - [%s Source code (%s)](%s) %n", new Emoji("\uD83D\uDCC1"), source.getFormat(), source.getUrl()));
        }
        return context.toString();
    }
}
