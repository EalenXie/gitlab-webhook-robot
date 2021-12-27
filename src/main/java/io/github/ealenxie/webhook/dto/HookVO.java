package io.github.ealenxie.webhook.dto;

/**
 * Created by EalenXie on 2021/12/10 11:51
 */
public interface HookVO {


    default String getUserHomePage(String projectUrl, String username) {
        String schema = projectUrl.substring(0, projectUrl.indexOf("//"));
        String body = projectUrl.substring(projectUrl.indexOf("//") + 2);
        String host = body.substring(0, body.indexOf("/"));
        return schema + host + "/" + username;
    }
}
