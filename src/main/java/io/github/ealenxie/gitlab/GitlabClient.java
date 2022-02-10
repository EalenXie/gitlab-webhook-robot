package io.github.ealenxie.gitlab;

import io.github.ealenxie.gitlab.dto.GitlabUser;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by EalenXie on 2022/2/10 14:11
 */
public class GitlabClient {
    /**
     * gitlab域名 例如 http://xxx.gitlab 或 http://192.168.1.1:8090
     */
    private final String host;

    private final RestOperations restOperations;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    public GitlabClient(String host, String privateToken) {
        this(host, privateToken, new RestTemplate());
    }

    public GitlabClient(String host, String privateToken, RestOperations restOperations) {
        this.host = host;
        this.restOperations = restOperations;
        httpHeaders.setBearerAuth(privateToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * 调用Gitlab 获取用户接口
     *
     * @param userId 用户Id
     */
    public ResponseEntity<GitlabUser> getUserById(Long userId) {
        return restOperations.exchange(URI.create(String.format("%s/api/v4/users/%s", host, userId)), HttpMethod.GET, new HttpEntity<>(null, httpHeaders), GitlabUser.class);
    }

}
