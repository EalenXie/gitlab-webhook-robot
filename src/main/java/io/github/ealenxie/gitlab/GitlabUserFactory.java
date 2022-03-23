package io.github.ealenxie.gitlab;

import io.github.ealenxie.gitlab.vo.GitlabUser;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by EalenXie on 2022/3/4 13:18
 */
public class GitlabUserFactory {

    private final GitlabClient gitlabClient;

    private final Map<Long, GitlabUser> gitlabUsers = new HashMap<>();

    public GitlabUserFactory(GitlabClient gitlabClient) {
        this.gitlabClient = gitlabClient;
    }

    @Nullable
    public GitlabUser getUser(Long userId) {
        if (!gitlabUsers.containsKey(userId)) {
            try {
                ResponseEntity<GitlabUser> response = gitlabClient.getUserById(userId);
                GitlabUser body = response.getBody();
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
}
