package io.github.ealenxie.gitlab.webhook.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;
import io.github.ealenxie.gitlab.webhook.tool.FileConvert;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by EalenXie on 2021/12/1 9:25
 */
@Getter
@Setter
public class PipelineHook implements MarkDownMsg {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    @JsonProperty("merge_request")
    private String mergeRequest;
    private User user;
    private Project project;
    private Commit commit;
    private List<Build> builds;

    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @SuppressWarnings("all")
    @Override
    public String getMarkdown() {
        StringBuilder sb = new StringBuilder();
        String status = objectAttributes.getStatus();
        String pipeline = String.format("%s [#%s %s](%s/-/pipelines/%s)", objectKind, objectAttributes.getId(), new Emoji("\uD83D\uDE80"), project.getWebUrl(), objectAttributes.getId());
        sb.append(String.format("[[%s:%s]](%s/-/tree/%s) <font color='#000000'>%s %s</font>%n%n", project.getName(), objectAttributes.getRef(), project.getWebUrl(), getObjectAttributes().getRef(), pipeline, status));
        if (!"running".equals(status)) {
            int totalTime = 0;
            if (objectAttributes.getDuration() != null) {
                totalTime += objectAttributes.getDuration();
            }
            if (objectAttributes.getQueuedDuration() != null) {
                totalTime += objectAttributes.getQueuedDuration();
            }
            sb.append(String.format(">[%s](%s) %s - %s%n%n", commit.getId().substring(0, 8), commit.getUrl(), commit.getAuthor().getName(), commit.getTitle()));
            Emoji statusEmoji = new Emoji();
            String statusColor = "";
            switch (status) {
                case "success":
                    statusEmoji.setCode("✅");
                    statusColor = "#00b140";
                    break;
                case "failed":
                    statusEmoji.setCode("❌");
                    statusColor = "#ff0000";
                    break;
                case "canceled":
                    statusEmoji.setCode("⏹️");
                    statusColor = "#FFDAC8";
                    break;
                case "skipped":
                    statusEmoji.setCode("⏭️");
                    statusColor = "#8E8E8E";
                    break;
                default:
                    break;
            }
            sb.append(String.format("%s%s : <font color='%s'>%s</font> %s %ss%n%n", statusEmoji, pipeline, statusColor, objectAttributes.getDetailedStatus(), new Emoji("\uD83D\uDD57"), totalTime));
            Collections.sort(builds);
            for (Build build : builds) {
                String costTime = String.format("%.0f", build.getDuration());
                if (costTime.equals("")) {
                    if (build.getFinishedAt() != null && build.getStartedAt() != null) {
                        Date finishedAt = Date.from(LocalDateTime.parse(build.getFinishedAt().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
                        Date start = Date.from(LocalDateTime.parse(build.getStartedAt().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
                        costTime = String.valueOf((finishedAt.getTime() - start.getTime()) / 1000);
                    } else {
                        costTime = "0";
                    }
                }
                String color = "";
                Emoji emoji = new Emoji();
                switch (build.getStatus()) {
                    case "success":
                        color = "#00b140";
                        emoji.setCode("✔️");
                        break;
                    case "failed":
                        color = "#ff0000";
                        emoji.setCode("❌");
                        break;
                    case "canceled":
                        color = "#FFDAC8";
                        emoji.setCode("⏹️");
                        break;
                    case "skipped":
                        color = "#8E8E8E";
                        emoji.setCode("⏭️");
                        break;
                    default:
                        break;
                }
                String fileName = "";
                if (build.getArtifactFile().getFilename() != null && build.getArtifactFile().getSize() != null) {
                    fileName = String.format("[%s](%s/-/jobs/%s/artifacts/download) %s", build.getArtifactFile().getFilename(), project.getWebUrl(), build.getId(), FileConvert.getFormatFileSize(build.getArtifactFile().getSize()));
                }
                sb.append(String.format(">%s [%s](%s/-/jobs/%s) : <font color='%s'>%s</font> %s %s %ss%n%n", emoji, build.getStage(), project.getWebUrl(), build.getId(), color, build.getStatus(), fileName, new Emoji("\uD83D\uDD57"), costTime));
            }
        }
        return sb.toString();
    }


    @Getter
    @Setter
    public static class ObjectAttributes {
        private Long id;
        private String ref;
        private Boolean tag;
        private String sha;
        @JsonProperty("before_sha")
        private String beforeSha;
        private String source;
        private String status;
        @JsonProperty("detailed_status")
        private String detailedStatus;
        private String[] stages;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("finished_at")
        private String finishedAt;
        private Double duration;
        @JsonProperty("queued_duration")
        private Double queuedDuration;
        private String[] variables;
    }
}
