package io.github.ealenxie.gitlab.webhook.dto;

/**
 * Created by EalenXie on 2021/12/1 10:00
 */
public class ArtifactsFile {


    private String filename;

    private Long size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
