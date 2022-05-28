package io.github.ealenxie.feishu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/2/15 12:54
 */
@NoArgsConstructor
public class DivElement implements Element {

    @JsonProperty("tag")
    private String tag = "div";
    @Getter
    @Setter
    @JsonProperty("text")
    private Text text;

    public DivElement(Text text) {
        this.text = text;
    }

    @Override
    public String getTag() {
        return tag;
    }

}
