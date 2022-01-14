package io.github.ealenxie.dingtalk.dto;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/27 11:13
 */
public class FeedCard {

    private List<Link> links;

    public FeedCard() {
    }

    public FeedCard(List<Link> links) {
        this.links = links;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
