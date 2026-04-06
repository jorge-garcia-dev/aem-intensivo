package com.itv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.Self;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HighlightsModel {

    @Self
    private Resource resource;

    private List<Card> cards = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Resource cardsResource = resource.getChild("card");
        if (cardsResource != null) {
            for (Resource item : cardsResource.getChildren()) {
                String icon = item.getValueMap().get("icon", String.class);
                String value = item.getValueMap().get("value", String.class);
                String tag = item.getValueMap().get("tag", String.class);
                if (value != null && tag != null) {
                    cards.add(new Card(icon, value, tag));
                }
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public static class Card {
        private String icon;
        private String value;
        private String tag;

        public Card(String icon, String value, String tag) {
            this.icon = icon;
            this.value = value;
            this.tag = tag;
        }

        public String getIcon() { return icon; }
        public String getValue() { return value; }
        public String getTag() { return tag; }
    }
}