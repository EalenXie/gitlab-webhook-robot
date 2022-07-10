package io.github.ealenxie.dingtalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 11:06
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    private String text;
    private String title;
    private String picUrl;
    private String messageUrl;

}
