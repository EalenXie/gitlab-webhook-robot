
package io.github.ealenxie.webhook;

import io.github.ealenxie.client.gitlab.dto.PipelineCancelDeleteDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/1 9:17
 */
@Controller
public class GitlabEndpoint {

    public static final String PIPELINE_CANCEL_DELETE_URL = "/actuator/gitlab/pipeline/cancel/delete";

    /**
     * 默认的404页面 😄
     */
    public static final String NOT_FOUND_URL = "https://github.com/EalenXie/gitlab-webhook-robot";

    @Resource
    private GitlabHandlerRepository gitlabHandlerRepository;

    /**
     * 取消 重试 删除pipeline
     */
    @GetMapping(PIPELINE_CANCEL_DELETE_URL + "/{id}")
    public String pipelineCancelRemove(@PathVariable String id, PipelineCancelDeleteDTO dto) {
        GitlabHandler gitlabHandler = gitlabHandlerRepository.findByWebhook(id);
        if (gitlabHandler == null) {
            return NOT_FOUND_URL;
        }
        return String.format("redirect:%s", gitlabHandler.pipelineCancelDelete(dto));
    }
}
