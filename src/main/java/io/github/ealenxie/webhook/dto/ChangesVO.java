package io.github.ealenxie.webhook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by EalenXie on 2021/12/10 10:43
 */
public class ChangesVO {

    @JsonProperty("merge_status")
    private Map<String, Object> mergeStatus;

    private Map<String, Object> confidential;

    @JsonProperty("updated_at")
    private Map<String, Object> updatedAt;

    public Map<String, Object> getMergeStatus() {
        return mergeStatus;
    }

    public void setMergeStatus(Map<String, Object> mergeStatus) {
        this.mergeStatus = mergeStatus;
    }

    public Map<String, Object> getConfidential() {
        return confidential;
    }

    public void setConfidential(Map<String, Object> confidential) {
        this.confidential = confidential;
    }

    public Map<String, Object> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Map<String, Object> updatedAt) {
        this.updatedAt = updatedAt;
    }
}
