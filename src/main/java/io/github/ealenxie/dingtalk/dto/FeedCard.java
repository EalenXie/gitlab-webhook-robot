package io.github.ealenxie.dingtalk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/27 11:13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedCard {
    private List<Link> links;
}
