package com.itv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.Self;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

    @Self
    private Resource resource;

    private List<NavItem> navItems = new ArrayList<>();

    @PostConstruct
    protected void init() {
        Resource navItemsResource = resource.getChild("navItems");
        if (navItemsResource != null) {
            for (Resource item : navItemsResource.getChildren()) {
                String text = item.getValueMap().get("text", String.class);
                String navLink = item.getValueMap().get("navLink", String.class);
                if (text != null && navLink != null) {
                    navItems.add(new NavItem(text, navLink));
                }
            }
        }
    }

    public List<NavItem> getNavItems() {
        return navItems;
    }

    public static class NavItem {
        private String text;
        private String navLink;

        public NavItem(String text, String navLink) {
            this.text = text;
            this.navLink = navLink;
        }

        public String getText() { return text; }
        public String getNavLink() { return navLink; }
    }
}