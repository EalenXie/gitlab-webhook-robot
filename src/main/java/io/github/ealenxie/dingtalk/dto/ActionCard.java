package io.github.ealenxie.dingtalk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/27 10:56
 */
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
     * 希望在外部浏览器打开，请参考消息链接说明https://open.dingtalk.com/document/orgapp-server/message-link-description
     */
    private String singleURL;


    @JsonProperty("btns")
    private List<Btn> btnList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBtnOrientation() {
        return btnOrientation;
    }

    public void setBtnOrientation(String btnOrientation) {
        this.btnOrientation = btnOrientation;
    }

    public List<Btn> getBtnList() {
        return btnList;
    }

    public void setBtnList(List<Btn> btnList) {
        this.btnList = btnList;
    }

    public String getSingleTitle() {
        return singleTitle;
    }

    public void setSingleTitle(String singleTitle) {
        this.singleTitle = singleTitle;
    }

    public String getSingleURL() {
        return singleURL;
    }

    public void setSingleURL(String singleURL) {
        this.singleURL = singleURL;
    }
}
