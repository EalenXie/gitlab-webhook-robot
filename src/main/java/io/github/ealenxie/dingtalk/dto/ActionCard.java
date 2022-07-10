package io.github.ealenxie.dingtalk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/27 10:56
 */
@Getter
@Setter
public class ActionCard {
    private String title;
    private String text;
    /**
     * 0：按钮竖直排列
     * 1：按钮横向排列
     */
    private String btnOrientation;

    /**
     * 单个按钮的标题。
     */
    private String singleTitle;

    /**
     * 点击消息跳转的URL，打开方式如下：
     * 移动端，在钉钉客户端内打开
     * PC端 默认侧边栏打开
     * 希望在外部浏览器打开，请参考消息链接说明 <a href="https://open.dingtalk.com/document/orgapp-server/message-link-description">...</a>
     */
    private String singleURL;

    @JsonProperty("btns")
    private List<Btn> btnList;


}
