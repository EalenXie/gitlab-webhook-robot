package io.github.ealenxie.dingtalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/10/27 10:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Btn {
    private String title;
    private String actionURL;
}
