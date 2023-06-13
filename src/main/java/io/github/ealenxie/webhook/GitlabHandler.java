package io.github.ealenxie.webhook;

import io.github.ealenxie.client.gitlab.GitlabClient;
import io.github.ealenxie.client.gitlab.dto.PipelineCancelDeleteDTO;
import io.github.ealenxie.client.gitlab.vo.CancelPipeline;
import io.github.ealenxie.client.gitlab.vo.GitlabUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EalenXie on 2022/7/16 21:04
 */
@Slf4j
public class GitlabHandler {

    private final GitlabClient gitlabClient;


    public GitlabHandler(GitlabClient gitlabClient) {
        this.gitlabClient = gitlabClient;
    }

    private final Map<Long, GitlabUser> gitlabUsers = new HashMap<>();


    @Nullable
    public GitlabUser getUser(Long userId) {
        if (!gitlabUsers.containsKey(userId)) {
            try {
                GitlabUser body = gitlabClient.getUserById(userId);
                gitlabUsers.put(userId, body);
            } catch (Exception e) {
                gitlabUsers.put(userId, new GitlabUser());
            }
        }
        return gitlabUsers.get(userId);
    }


    @Nullable
    public String getUserSkype(Long userId) {
        GitlabUser gitlabUser = getUser(userId);
        if (gitlabUser != null && !ObjectUtils.isEmpty(gitlabUser.getSkype())) {
            return gitlabUser.getSkype();
        }
        return null;
    }

    public String pipelineCancelDelete(PipelineCancelDeleteDTO dto) {
        try {
            CancelPipeline cancelPipeline = gitlabClient.cancelPipeline(dto.getProjectId(), dto.getPipelineId());
            String webUrl = cancelPipeline.getWebUrl();
            if (dto.getAction().equals("retry")) {
                CancelPipeline pipeline = gitlabClient.retryPipeline(dto.getProjectId(), dto.getPipelineId());
                webUrl = pipeline.getWebUrl();
            } else if (dto.getAction().equals("delete")) {
                gitlabClient.deletePipeline(dto.getProjectId(), dto.getPipelineId());
                webUrl = webUrl.substring(0, webUrl.lastIndexOf("/"));
            }
            return webUrl;
        } catch (HttpStatusCodeException e) {
            log.warn("call gitlab error , statusCode:{}, body:{}", e.getRawStatusCode(), e.getResponseBodyAsString());
            return gitlabClient.getHost();
        }
    }

}
