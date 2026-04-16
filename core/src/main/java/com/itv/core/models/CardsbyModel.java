package com.itv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardsbyModel {

    @Self
    private Resource resource;

    private List<CardItem> cards = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Resource cardsResource = resource.getChild("cards");
        if (cardsResource != null) {
            for (Resource cardResource : cardsResource.getChildren()) {
                CardItem item = cardResource.adaptTo(CardItem.class);
                if (item != null) {
                    cards.add(item);
                }
            }
        }
    }

    public List<CardItem> getCards() {
        return cards;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class CardItem {

        @ValueMapValue
        private String image;

        @ValueMapValue
        private String nombre;

        @ValueMapValue
        private String precio;

        @ValueMapValue
        private String descripcion;

        @ValueMapValue
        private String ctaTexto;

        @ValueMapValue
        private String ctaEnlace;

        public String getImage() { return image; }
        public String getNombre() { return nombre; }
        public String getPrecio() { return precio; }
        public String getDescripcion() { return descripcion; }
        public String getCtaTexto() { return ctaTexto; }
        public String getCtaEnlace() { return ctaEnlace; }
    }
}