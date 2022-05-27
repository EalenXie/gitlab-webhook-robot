package io.github.ealenxie.gitlab.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/5/27 14:37
 */
@Getter
@Setter
public class PipelineCancelDeleteDTO {
    private Long projectId;
    private Long pipelineId;
    private String action;
}
