package com.itv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.Self;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class EquipoModel {
    
     @Self
    private Resource resource;

    private List<Card> cards = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Resource cardsResource = resource.getChild("card");
        if (cardsResource != null) {
            for (Resource item : cardsResource.getChildren()) {
                String image = item.getValueMap().get("image", String.class);
                String title = item.getValueMap().get("title", String.class);
                String text = item.getValueMap().get("text", String.class);
                if (title != null && text != null) {
                    cards.add(new Card(image, title, text));
                }
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public static class Card {
        private String image;
        private String title;
        private String text;

        public Card(String image, String title, String text) {
            this.image = image;
            this.title = title;
            this.text = text;
        }

        public String getImage() { return image; }
        public String getTitle() { return title; }
        public String getText() { return text; }
    }
}
