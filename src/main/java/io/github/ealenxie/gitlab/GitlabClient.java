package io.github.ealenxie.gitlab;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ealenxie.gitlab.dto.PipelinesDTO;
import io.github.ealenxie.gitlab.vo.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by EalenXie on 2022/2/10 14:11
 */
public class GitlabClient {
    /**
     * gitlab域名 例如 http://xxx.gitlab
     */
    private final String host;

    private final RestOperations restOperations;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    private final ObjectMapper objectMapper = new ObjectMapper();

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
    public GitlabUser getUserById(Long userId) {
        return restOperations.exchange(URI.create(String.format("%s/api/v4/users/%s", host, userId)), HttpMethod.GET, new HttpEntity<>(null, httpHeaders), GitlabUser.class).getBody();
    }


    /**
     * 调用Gitlab 根据组Id获取用户成员信息
     *
     * @param groupId 组Id
     */
    public List<Member> getAllMembersByGroupId(Long groupId) {
        return restOperations.exchange(URI.create(String.format("%s/api/v4/groups/%s/members/all", host, groupId)), HttpMethod.GET, new HttpEntity<>(null, httpHeaders), new ParameterizedTypeReference<List<Member>>() {
        }).getBody();
    }


    /**
     * 调用Gitlab 根据组Id获取用户成员信息
     *
     * @param projectId 项目Id
     */
    public List<Member> getAllMembersByProjectId(Long projectId) {
        return restOperations.exchange(URI.create(String.format("%s/api/v4/projects/%s/members/all", host, projectId)), HttpMethod.GET, new HttpEntity<>(null, httpHeaders), new ParameterizedTypeReference<List<Member>>() {
        }).getBody();
    }


    /**
     * 调用Gitlab 根据组Id获取Jobs
     *
     * @param projectId 项目Id
     * @param scope     Scope of jobs to show. Either one of or an array of the following: created, pending, running, failed, success, canceled, skipped, or manual. All jobs are returned if scope is not provided.
     */
    public List<Job> getJobsByProjectId(Long projectId, @Nullable JobScope scope) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/api/v4/projects/%s/jobs", host, projectId));
        if (scope != null) {
            builder.queryParam("scope", scope.name());
        }
        URI uri = builder.build().encode().toUri();
        return restOperations.exchange(uri, HttpMethod.GET, new HttpEntity<>(null, httpHeaders), new ParameterizedTypeReference<List<Job>>() {
        }).getBody();
    }

    /**
     * 调用Gitlab 移除JOB
     *
     * @param projectId 项目Id
     * @param jobId     JobId
     */
    public EraseJob eraseJob(Long projectId, Long jobId) {
        return restOperations.exchange(String.format("%s/api/v4/projects/%s/jobs/%s/erase", host, projectId, jobId), HttpMethod.POST, new HttpEntity<>(null, httpHeaders), EraseJob.class).getBody();
    }


    /**
     * 调用Gitlab 获取pipelines
     *
     * @param projectId 项目Id
     * @param dto       请求参数
     */
    public List<Pipeline> getPipelinesByProjectId(Long projectId, @Nullable PipelinesDTO dto) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/api/v4/projects/%s/pipelines", host, projectId));
        if (dto != null) {
            Map<String, String> args = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>() {
            });
            LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
            queryParams.setAll(args);
            builder.queryParams(queryParams);
        }
        URI uri = builder.build().encode().toUri();
        return restOperations.exchange(uri, HttpMethod.GET, new HttpEntity<>(null, httpHeaders), new ParameterizedTypeReference<List<Pipeline>>() {
        }).getBody();
    }


    /**
     * 取消pipeline
     *
     * @param projectId  项目Id
     * @param pipelineId pipelineId
     */
    public CancelPipeline cancelPipeline(Long projectId, Long pipelineId) {
        return restOperations.exchange(String.format("%s/api/v4/projects/%s/pipelines/%s/cancel", host, projectId, pipelineId), HttpMethod.DELETE, new HttpEntity<>(null, httpHeaders), CancelPipeline.class).getBody();
    }

    /**
     * 删除pipeline
     *
     * @param projectId  项目Id
     * @param pipelineId pipelineId
     */
    public void deletePipeline(Long projectId, Long pipelineId) {
        restOperations.exchange(String.format("%s/api/v4/projects/%s/pipelines/%s", host, projectId, pipelineId), HttpMethod.DELETE, new HttpEntity<>(null, httpHeaders), Void.class);
    }
}
