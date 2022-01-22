package io.github.ealenxie.webhook.dto;

/**
 * Created by EalenXie on 2021/12/1 10:00
 */
public class ArtifactsFile {


    private String filename;

    private String size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
