
package io.github.ealenxie.gitlab;

import io.github.ealenxie.gitlab.config.GitlabConfig;
import io.github.ealenxie.gitlab.dto.PipelineCancelDeleteDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/1 9:17
 */
@Controller
@ConditionalOnProperty(prefix = GitlabConfig.PREFIX, value = "enable", havingValue = "true")
public class GitlabEndpoint {

    public static final String PIPELINE_CANCEL_DELETE_URL = "/actuator/gitlab/pipeline/cancel/delete";
    @Resource
    private GitlabHandler gitlabHandler;

    /**
     * 取消 重试 删除pipeline
     */
    @GetMapping(PIPELINE_CANCEL_DELETE_URL)
    public String pipelineCancelRemove(PipelineCancelDeleteDTO dto) {
        return String.format("redirect:%s", gitlabHandler.pipelineCancelDelete(dto));
    }
}
