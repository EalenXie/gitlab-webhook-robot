package io.github.ealenxie.webhook.dto;

import java.util.List;

/**
 * Created by EalenXie on 2022/1/20 10:05
 */
public class Assets {


    private Integer count;

    private String[] links;

    private List<Source> sources;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
