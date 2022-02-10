package io.github.ealenxie.gitlab;

import org.springframework.http.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by EalenXie on 2022/2/10 14:11
 */
public class GitlabClient {

    private final RestOperations restOperations;

    public GitlabClient() {
        restOperations = new RestTemplate();
    }

    public GitlabClient(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    /**
     * 调用Gitlab 获取用户接口
     *
     * @param host         gitlab域名 例如 http://xxx.gitlab 或 http://192.168.1.1:8090
     * @param privateToken privateToken
     */
    public ResponseEntity<String> getUserById(String host, Long userId, String privateToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(privateToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            return restOperations.exchange(URI.create(String.format("%s/api/v4/users/%s", host, userId)), HttpMethod.GET, new HttpEntity<>(null, httpHeaders), String.class);
        } catch (RestClientResponseException e) {
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        }
    }

}
