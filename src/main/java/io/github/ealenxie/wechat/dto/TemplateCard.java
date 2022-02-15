package io.github.ealenxie.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public static class Source {
        @JsonProperty("icon_url")
        private String iconUrl;
        @JsonProperty("desc")
        private String desc;
        @JsonProperty("desc_color")
        private Integer descColor;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Integer getDescColor() {
            return descColor;
        }

        public void setDescColor(Integer descColor) {
            this.descColor = descColor;
        }
    }

    public static class QuoteArea {
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("url")
        private String url;
        @JsonProperty("appid")
        private String appid;
        @JsonProperty("pagepath")
        private String pagePath;
        @JsonProperty("title")
        private String title;
        @JsonProperty("quote_text")
        private String quoteText;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagePath() {
            return pagePath;
        }

        public void setPagePath(String pagePath) {
            this.pagePath = pagePath;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuoteText() {
            return quoteText;
        }

        public void setQuoteText(String quoteText) {
            this.quoteText = quoteText;
        }
    }

    public static class ImageTextArea {

        @JsonProperty("type")
        private Integer type;
        @JsonProperty("url")
        private String url;
        @JsonProperty("title")
        private String title;
        @JsonProperty("desc")
        private String desc;
        @JsonProperty("image_url")
        private String imageUrl;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public static class HorizontalContent {
        @JsonProperty("keyname")
        private String keyName;
        @JsonProperty("value")
        private String value;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("url")
        private String url;
        @JsonProperty("media_id")
        private String mediaId;

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
    }

    public static class EmphasisContent {
        @JsonProperty("title")
        private String title;
        @JsonProperty("desc")
        private String desc;

        public EmphasisContent() {
        }

        public EmphasisContent(String title, String desc) {
            this.title = title;
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class CardAction {
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("url")
        private String url;
        @JsonProperty("appid")
        private String appid;
        @JsonProperty("pagepath")
        private String pagePath;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagePath() {
            return pagePath;
        }

        public void setPagePath(String pagePath) {
            this.pagePath = pagePath;
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardImage {
        @JsonProperty("url")
        private String url;
        @JsonProperty("aspect_ratio")
        private Double aspectRatio;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MainTitle {
        @JsonProperty("title")
        private String title;
        @JsonProperty("desc")
        private String desc;

    }

    @Getter
    @Setter
    public static class Jump {
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("url")
        private String url;
        @JsonProperty("title")
        private String title;
        @JsonProperty("appid")
        private String appid;
        @JsonProperty("pagepath")
        private String pagePath;
    }

    @Getter
    @Setter
    public static class VerticalContent {
        @JsonProperty("title")
        private String title;
        @JsonProperty("desc")
        private String desc;
    }
}
