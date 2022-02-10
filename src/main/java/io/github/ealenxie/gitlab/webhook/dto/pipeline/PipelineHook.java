package io.github.ealenxie.gitlab.webhook.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.gitlab.webhook.dto.*;
import io.github.ealenxie.gitlab.webhook.tool.FileConvert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by EalenXie on 2021/12/1 9:25
 */
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

    public String getObjectKind() {
        return objectKind;
    }

    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }


    public String getMergeRequest() {
        return mergeRequest;
    }

    public void setMergeRequest(String mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    public ObjectAttributes getObjectAttributes() {
        return objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributes objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public List<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(List<Build> builds) {
        this.builds = builds;
    }


    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @SuppressWarnings("all")
    @Override
    public String getMarkdown() {
        StringBuilder sb = new StringBuilder();
        if (objectAttributes != null) {
            String status = objectAttributes.getStatus();
            String pipeline = String.format("%s [#%s %s](%s/-/pipelines/%s)", objectKind, objectAttributes.getId(), new Emoji("\uD83D\uDE80"), project.getWebUrl(), objectAttributes.getId());
            sb.append(String.format("[[%s:%s]](%s/-/tree/%s) <font color='#000000'>%s %s</font>%n%n", project.getName(), objectAttributes.getRef(), project.getWebUrl(), getObjectAttributes().getRef(), pipeline, status));
            if (!"running".equals(status)) {
                int totalTime = (int) (objectAttributes.getDuration() + objectAttributes.getQueuedDuration());
                sb.append(String.format(">[%s](%s) %s - %s%n%n", commit.getId().substring(0, 8), commit.getUrl(), commit.getAuthor().getName(), commit.getTitle()));
                Emoji statusEmoji = new Emoji();
                String statusColor = "";
                if (Objects.equals(status, "success")) {
                    statusEmoji.setCode("✅");
                    statusColor = "#00b140";
                } else if (Objects.equals(status, "failed")) {
                    statusEmoji.setCode("❌");
                    statusColor = "#ff0000";
                } else if (Objects.equals(status, "canceled")) {
                    statusEmoji.setCode("⏹️");
                    statusColor = "#FFDAC8";
                } else if (Objects.equals(status, "skipped")) {
                    statusEmoji.setCode("⏭️");
                    statusColor = "#8E8E8E";
                }
                sb.append(String.format("%s%s : <font color='%s'>%s</font> %s %ss%n%n", statusEmoji, pipeline, statusColor, objectAttributes.getDetailedStatus(), new Emoji("\uD83D\uDD57"), totalTime));
                Collections.sort(builds);
                for (Build build : builds) {
                    String costTime = String.format("%.0f", build.getDuration());
                    if (costTime.equals("")) {
                        costTime = "0";
                    }
                    String color = "";
                    Emoji emoji = new Emoji();
                    if (Objects.equals(build.getStatus(), "success")) {
                        color = "#00b140";
                        emoji.setCode("✔️");
                    } else if (Objects.equals(build.getStatus(), "failed")) {
                        color = "#ff0000";
                        emoji.setCode("❌");
                    } else if (Objects.equals(build.getStatus(), "canceled")) {
                        color = "#FFDAC8";
                        emoji.setCode("⏹️");
                    } else if (Objects.equals(build.getStatus(), "skipped")) {
                        color = "#8E8E8E";
                        emoji.setCode("⏭️");
                    }
                    String fileName = "";
                    if (build.getArtifactFile().getFilename() != null && build.getArtifactFile().getSize() != null) {
                        fileName = String.format("[%s](%s/-/jobs/%s/artifacts/download) %s", build.getArtifactFile().getFilename(), project.getWebUrl(), build.getId(), FileConvert.getFormatFileSize(build.getArtifactFile().getSize()));
                    }
                    sb.append(String.format(">%s [%s](%s/-/jobs/%s) : <font color='%s'>%s</font> %s %s %ss%n%n", emoji, build.getStage(), project.getWebUrl(), build.getId(), color, build.getStatus(), fileName, new Emoji("\uD83D\uDD57"), costTime));
                }
            }
        }
        return sb.toString();
    }
}
