package com.itv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.Self;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ValoresModel {
    

    @Self
    private Resource resource;

    private List<Card> cards = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Resource cardsResource = resource.getChild("card");
        if (cardsResource != null) {
            for (Resource item : cardsResource.getChildren()) {
                String number = item.getValueMap().get("number", String.class);
                String title = item.getValueMap().get("title", String.class);
                String text = item.getValueMap().get("text", String.class);
                if (title != null && text != null) {
                    cards.add(new Card(number, title, text));
                }
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public static class Card {
        private String number;
        private String title;
        private String text;

        public Card(String number, String title, String text) {
            this.number = number;
            this.title = title;
            this.text = text;
        }

        public String getNumber() { return number; }
        public String getTitle() { return title; }
        public String getText() { return text; }
    }
}
