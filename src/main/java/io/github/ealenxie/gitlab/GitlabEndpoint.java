
package io.github.ealenxie.gitlab;

import io.github.ealenxie.gitlab.dto.PipelineCancelDeleteDTO;
import io.github.ealenxie.gitlab.vo.CancelPipeline;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2021/12/1 9:17
 */
@Controller
public class GitlabEndpoint {


    public static final String PIPELINE_CANCEL_DELETE_URL = "/actuator/gitlab/pipeline/cancel/delete";

    @Resource
    private GitlabHandler gitlabHandler;


    @GetMapping(PIPELINE_CANCEL_DELETE_URL)
    public String pipelineCancelRemove(PipelineCancelDeleteDTO dto) {
        CancelPipeline cancelPipeline = gitlabHandler.pipelineCancelDelete(dto);
        return String.format("redirect:%s", cancelPipeline.getWebUrl());
    }
}
