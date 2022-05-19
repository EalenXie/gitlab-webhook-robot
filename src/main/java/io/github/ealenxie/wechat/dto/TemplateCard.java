package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TemplateCard {
    @JsonProperty("card_type")
    private String cardType;
    @JsonProperty("source")
    private Source source;
    @JsonProperty("main_title")
    private MainTitle mainTitle;
    @JsonProperty("card_image")
    private CardImage cardImage;
    @JsonProperty("emphasis_content")
    private EmphasisContent emphasisContent;
    @JsonProperty("image_text_area")
    private ImageTextArea imageTextArea;
    @JsonProperty("quote_area")
    private QuoteArea quoteArea;
    @JsonProperty("sub_title_text")
    private String subTitleText;
    @JsonProperty("vertical_content_list")
    private List<VerticalContent> verticalContents;
    @JsonProperty("horizontal_content_list")
    private List<HorizontalContent> horizontalContents;
    @JsonProperty("jump_list")
    private List<Jump> jump;
    @JsonProperty("card_action")
    private CardAction cardAction;

}