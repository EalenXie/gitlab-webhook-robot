package io.github.ealenxie.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Setter
    @Getter
    public static class TemplateCard {
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


        @Getter
        @Setter
        public static class Source {
            @JsonProperty("icon_url")
            private String iconUrl;
            @JsonProperty("desc")
            private String desc;
            @JsonProperty("desc_color")
            private Integer descColor;
        }

        @Getter
        @Setter
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
        }

        @Getter
        @Setter
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
        }

        @Getter
        @Setter
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
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EmphasisContent {
            @JsonProperty("title")
            private String title;
            @JsonProperty("desc")
            private String desc;
        }

        @Getter
        @Setter
        public static class CardAction {
            @JsonProperty("type")
            private Integer type;
            @JsonProperty("url")
            private String url;
            @JsonProperty("appid")
            private String appid;
            @JsonProperty("pagepath")
            private String pagePath;
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

}
