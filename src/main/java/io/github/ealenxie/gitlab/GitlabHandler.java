package io.github.ealenxie.gitlab;

import io.github.ealenxie.gitlab.config.GitlabConfig;
import io.github.ealenxie.gitlab.dto.PipelineCancelDeleteDTO;
import io.github.ealenxie.gitlab.vo.CancelPipeline;
import io.github.ealenxie.gitlab.vo.GitlabUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EalenXie on 2022/3/4 13:18
 */
@ConditionalOnProperty(prefix = GitlabConfig.PREFIX, value = "enable", havingValue = "true")
@Component
public class GitlabHandler {

    private final GitlabClient gitlabClient;

    private final Map<Long, GitlabUser> gitlabUsers = new HashMap<>();

    public GitlabHandler(GitlabConfig gitlabConfig) {
        this.gitlabClient = new GitlabClient(gitlabConfig.getHost(), gitlabConfig.getPrivateToken());
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

    public CancelPipeline pipelineCancelDelete(PipelineCancelDeleteDTO dto) {
        CancelPipeline cancelPipeline = gitlabClient.cancelPipeline(dto.getProjectId(), dto.getPipelineId());
        if (dto.getAction().equals("delete")) {
            gitlabClient.deletePipeline(dto.getProjectId(), dto.getPipelineId());
        }
        return cancelPipeline;
    }
}
