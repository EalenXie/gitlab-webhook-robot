
package io.github.ealenxie.webhook;

import io.github.ealenxie.client.gitlab.dto.PipelineCancelDeleteDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by EalenXie on 2021/12/1 9:17
 */
@Controller
public class GitlabEndpoint {

    public static final String PIPELINE_CANCEL_DELETE_URL = "/actuator/gitlab/pipeline/cancel/delete";

//
//    /**
//     * 取消 重试 删除pipeline
//     */
//    @GetMapping(PIPELINE_CANCEL_DELETE_URL+"/{id}")
//    public String pipelineCancelRemove(PipelineCancelDeleteDTO dto) {
//
//
//
//        return String.format("redirect:%s", gitlabHandler.pipelineCancelDelete(dto));
//    }
}
