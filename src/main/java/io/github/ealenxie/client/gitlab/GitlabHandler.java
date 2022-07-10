package io.github.ealenxie.client.gitlab;

import io.github.ealenxie.client.gitlab.dto.PipelineCancelDeleteDTO;
import io.github.ealenxie.client.gitlab.vo.CancelPipeline;
import io.github.ealenxie.client.gitlab.vo.GitlabUser;
import io.github.ealenxie.config.GitlabConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EalenXie on 2022/3/4 13:18
 */
@ConditionalOnProperty(prefix = GitlabConfig.PREFIX, value = "enable", havingValue = "true")
@Slf4j
@Component
public class GitlabHandler {

    private static boolean gitlabEnable = false;

    private final GitlabClient gitlabClient;

    private final GitlabConfig gitlabConfig;

    private final Map<Long, GitlabUser> gitlabUsers = new HashMap<>();

    public GitlabHandler(GitlabConfig gitlabConfig) {
        this.gitlabClient = new GitlabClient(gitlabConfig.getHost(), gitlabConfig.getPrivateToken());
        this.gitlabConfig = gitlabConfig;
        setGitlabEnable(true);
    }

    public static void setGitlabEnable(boolean enable) {
        gitlabEnable = enable;
    }

    public static boolean gitlabEnable() {
        return gitlabEnable;
    }

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
        if (gitlabUser != null && gitlabUser.getSkype() != null && !gitlabUser.getSkype().trim().equals("")) {
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
            return gitlabConfig.getHost();
        }
    }
}
