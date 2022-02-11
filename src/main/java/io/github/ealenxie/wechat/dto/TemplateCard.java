package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by EalenXie on 2022/2/11 13:20
 */
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public MainTitle getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(MainTitle mainTitle) {
        this.mainTitle = mainTitle;
    }

    public EmphasisContent getEmphasisContent() {
        return emphasisContent;
    }

    public void setEmphasisContent(EmphasisContent emphasisContent) {
        this.emphasisContent = emphasisContent;
    }

    public QuoteArea getQuoteArea() {
        return quoteArea;
    }

    public void setQuoteArea(QuoteArea quoteArea) {
        this.quoteArea = quoteArea;
    }

    public String getSubTitleText() {
        return subTitleText;
    }

    public void setSubTitleText(String subTitleText) {
        this.subTitleText = subTitleText;
    }

    public List<HorizontalContent> getHorizontalContents() {
        return horizontalContents;
    }

    public void setHorizontalContents(List<HorizontalContent> horizontalContents) {
        this.horizontalContents = horizontalContents;
    }

    public List<Jump> getJump() {
        return jump;
    }

    public void setJump(List<Jump> jump) {
        this.jump = jump;
    }

    public CardAction getCardAction() {
        return cardAction;
    }

    public void setCardAction(CardAction cardAction) {
        this.cardAction = cardAction;
    }

    public CardImage getCardImage() {
        return cardImage;
    }

    public void setCardImage(CardImage cardImage) {
        this.cardImage = cardImage;
    }

    public ImageTextArea getImageTextArea() {
        return imageTextArea;
    }

    public void setImageTextArea(ImageTextArea imageTextArea) {
        this.imageTextArea = imageTextArea;
    }

    public List<VerticalContent> getVerticalContents() {
        return verticalContents;
    }

    public void setVerticalContents(List<VerticalContent> verticalContents) {
        this.verticalContents = verticalContents;
    }
}
