package io.github.ealenxie.gitlab;

import io.github.ealenxie.gitlab.dto.GitlabUser;
import org.springframework.http.ResponseEntity;

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
}
