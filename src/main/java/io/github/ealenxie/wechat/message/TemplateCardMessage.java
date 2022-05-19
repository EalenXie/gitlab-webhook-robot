package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ealenxie.wechat.dto.TemplateCard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by EalenXie on 2022/2/11 15:58
 */
@NoArgsConstructor
public class TemplateCardMessage implements WeChatMessage {
    @Getter
    @JsonProperty("msgtype")
    private String msgType = "template_card";

    @Getter
    @Setter
    @JsonProperty("template_card")
    private TemplateCard templateCard;

    public TemplateCardMessage(TemplateCard templateCard) {
        this.templateCard = templateCard;
    }


}
