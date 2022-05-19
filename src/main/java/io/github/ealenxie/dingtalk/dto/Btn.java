package io.github.ealenxie.dingtalk.dto;

/**
 * Created by EalenXie on 2021/10/27 10:46
 */
public class Btn {

    private String title;

    private String actionURL;


    public Btn() {
    }

    public Btn(String title, String actionURL) {
        this.title = title;
        this.actionURL = actionURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActionURL() {
        return actionURL;
    }

    public void setActionURL(String actionURL) {
        this.actionURL = actionURL;
    }
}
