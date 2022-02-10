package io.github.ealenxie.gitlab.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EalenXie on 2021/12/1 11:35
 */
public class Repository {

    private String name;
    private String url;
    private String homepage;
    private String description;
    @JsonProperty("git_ssh_url")
    private String gitSshUrl;
    @JsonProperty("git_http_url")
    private String gitHttpUrl;
    @JsonProperty("visibility_level")
    private Integer visibilityLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGitSshUrl() {
        return gitSshUrl;
    }

    public void setGitSshUrl(String gitSshUrl) {
        this.gitSshUrl = gitSshUrl;
    }

    public String getGitHttpUrl() {
        return gitHttpUrl;
    }

    public void setGitHttpUrl(String gitHttpUrl) {
        this.gitHttpUrl = gitHttpUrl;
    }

    public Integer getVisibilityLevel() {
        return visibilityLevel;
    }

    public void setVisibilityLevel(Integer visibilityLevel) {
        this.visibilityLevel = visibilityLevel;
    }
}
