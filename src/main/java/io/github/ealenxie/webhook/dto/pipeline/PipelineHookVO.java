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
            String pipeline = objectKind+ "[#" + objectAttributes.getId() + " \uD83D\uDE80](" + project.getWebUrl() + "/-/pipelines/" + objectAttributes.getId() + ")";
            sb.append("[").append("[").append(project.getName()).append(":").append(objectAttributes.getRef()).append("]").append("](").append(project.getWebUrl()).append("/-/tree/").append(objectAttributes.getRef()).append(") ")
                    .append("<font color='#000000'>").append(pipeline).append(" ").append(status).append("</font>")
                    .append("\n\n");
            if (!"running".equals(status)) {
                int totalTime = (int) (objectAttributes.getDuration() + objectAttributes.getQueuedDuration());
                sb.append(">[").append(commit.getId(), 0, 8).append("]")
                        .append("(").append(commit.getUrl()).append(") ")
                        .append(commit.getAuthor().getName()).append(" - ").append(commit.getTitle());
                sb.append("</font>\n\n");
                if (Objects.equals(status, "success")) {
                    sb.append("✅").append(pipeline).append(" : ").append("<font color='#00b140'>").append(objectAttributes.getDetailedStatus()).append("</font>");
                } else if (Objects.equals(status, "failed")) {
                    sb.append("❌").append(pipeline).append(" : ").append("<font color='#ff0000'>").append(objectAttributes.getDetailedStatus()).append("</font>");
                } else if (Objects.equals(status, "canceled")) {
                    sb.append("⏹️").append(pipeline).append(" : ").append("<font color='#FFDAC8'>").append(objectAttributes.getDetailedStatus()).append("</font>");
                } else if (Objects.equals(status, "skipped")) {
                    sb.append("⏭️").append(pipeline).append(" : ").append("<font color='#8E8E8E'>").append(objectAttributes.getDetailedStatus()).append("</font>");
                }
                sb.append(" ").append("\uD83D\uDD57").append(totalTime).append("s").append("\n\n");
                Collections.sort(builds);
                for (BuildVO build : builds) {
                    String costTime = String.valueOf((build.getFinishedAt().getTime() - build.getStartedAt().getTime()) / 1000);
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
                    sb.append(">").append(emoji).append(" ");
                    sb.append("[").append(build.getStage()).append("]").append("(").append(project.getWebUrl()).append("/-/jobs/").append(build.getId()).append(")").append(" : ");
                    sb.append("<font color='").append(color).append("'>").append(build.getStatus()).append("</font> ");
                    sb.append("\uD83D\uDD57").append(costTime).append("s").append("\n\n");
                }
            }
        }
        return sb.toString();
    }
}
