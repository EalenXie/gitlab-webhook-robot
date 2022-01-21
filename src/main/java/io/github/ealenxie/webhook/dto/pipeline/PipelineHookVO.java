package io.github.ealenxie.webhook.dto.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.webhook.dto.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by EalenXie on 2021/12/1 9:25
 */
public class PipelineHookVO implements DingRobotActionCard {

    @JsonProperty("object_kind")
    private String objectKind;

    @JsonProperty("object_attributes")
    private ObjectAttributesVO objectAttributes;

    @JsonProperty("merge_request")
    private String mergeRequest;

    private UserVO user;

    private ProjectVO project;

    private CommitVO commit;

    private List<BuildVO> builds;


    public String getObjectKind() {
        return objectKind;
    }

    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    public ObjectAttributesVO getObjectAttributes() {
        return objectAttributes;
    }

    public void setObjectAttributes(ObjectAttributesVO objectAttributes) {
        this.objectAttributes = objectAttributes;
    }

    public String getMergeRequest() {
        return mergeRequest;
    }

    public void setMergeRequest(String mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public ProjectVO getProject() {
        return project;
    }

    public void setProject(ProjectVO project) {
        this.project = project;
    }

    public CommitVO getCommit() {
        return commit;
    }

    public void setCommit(CommitVO commit) {
        this.commit = commit;
    }

    public List<BuildVO> getBuilds() {
        return builds;
    }

    public void setBuilds(List<BuildVO> builds) {
        this.builds = builds;
    }


    @Override
    public String getTitle() {
        return getObjectKind();
    }

    @SuppressWarnings("all")
    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        if (objectAttributes != null) {
            String status = objectAttributes.getStatus();
            String pipeline = String.format("%s [#%s \uD83D\uDE80](%s/-/pipelines/%s)", objectKind, objectAttributes.getId(), project.getWebUrl(), objectAttributes.getId());
            sb.append(String.format("[[%s:%s]](%s/-/tree/%s) <font color='#000000'>%s %s</font>%n%n", project.getName(), objectAttributes.getRef(), project.getWebUrl(), getObjectAttributes().getRef(), pipeline, status));
            if (!"running".equals(status)) {
                int totalTime = (int) (objectAttributes.getDuration() + objectAttributes.getQueuedDuration());
                sb.append(String.format(">[%s](%s) %s - %s%n%n", commit.getId().substring(0, 8), commit.getUrl(), commit.getAuthor().getName(), commit.getTitle()));
                String statusEmoji = "";
                String statusColor = "";
                if (Objects.equals(status, "success")) {
                    statusEmoji = "✅";
                    statusColor = "#00b140";
                } else if (Objects.equals(status, "failed")) {
                    statusEmoji = "❌";
                    statusColor = "#ff0000";
                } else if (Objects.equals(status, "canceled")) {
                    statusEmoji = "⏹️";
                    statusColor = "#FFDAC8";
                } else if (Objects.equals(status, "skipped")) {
                    statusEmoji = "⏭️";
                    statusColor = "#8E8E8E";
                }
                sb.append(String.format("%s%s : <font color='%s'>%s</font> \uD83D\uDD57 %ss%n%n", statusEmoji, pipeline, statusColor, objectAttributes.getDetailedStatus(), totalTime));
                Collections.sort(builds);
                for (BuildVO build : builds) {
                    String costTime = String.format("%.0f", build.getDuration());
                    String color = "";
                    String emoji = "";
                    if (Objects.equals(build.getStatus(), "success")) {
                        color = "#00b140";
                        emoji = "✔️";
                    } else if (Objects.equals(build.getStatus(), "failed")) {
                        color = "#ff0000";
                        emoji = "❌";
                    } else if (Objects.equals(build.getStatus(), "canceled")) {
                        color = "#FFDAC8";
                        emoji = "⏹️";
                    } else if (Objects.equals(build.getStatus(), "skipped")) {
                        color = "#8E8E8E";
                        emoji = "⏭️";
                    }
                    sb.append(String.format(">%s [%s](%s/-/jobs/%s) : <font color='%s'>%s</font> \uD83D\uDD57%ss%n%n", emoji, build.getStage(), project.getWebUrl(), build.getId(), color, build.getStatus(), costTime));
                }
            }
        }
        return sb.toString();
    }
}
