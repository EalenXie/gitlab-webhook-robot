package io.github.ealenxie.gitlab.webhook.dto;

/**
 * Created by EalenXie on 2021/12/10 11:51
 */
public class UserUtils {

    private UserUtils() {
    }

    public static String getUserHomePage(String projectUrl, String username) {
        return String.format("%s/%s", getHostSchema(projectUrl), username);
    }

    public static String getHostSchema(String projectUrl) {
        String schema = projectUrl.substring(0, projectUrl.indexOf("//"));
        String body = projectUrl.substring(projectUrl.indexOf("//") + 2);
        String host = body.substring(0, body.indexOf("/"));
        return schema + host;
    }

}
