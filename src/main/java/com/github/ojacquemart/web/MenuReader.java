package com.github.ojacquemart.web;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MenuReader {
    public static final String FAMILY_SELECTOR = ".dishes-list";

    private String url = "http://auchan1.apimobile.fr/LesMenus";

    public MenuResponse getMenus() {
        log.info("Read menus");

        return readMenu();
    }

    private MenuResponse readMenu() {
        Document document = getDocument();

        List<Family> families = new ArrayList<>();

        Elements elements = document.select(FAMILY_SELECTOR);
        for (Element element : elements) {
            Elements subElements = element.select("li");
            String family = subElements.first().text();
            log.info("Family: {}", family);

            List<String> dishes = new ArrayList<>();
            for (int i = 1; i < subElements.size(); i++) {
                String dish = subElements.get(i).text();
                log.info("\tDish: {}", dish);

                dishes.add(dish);
            }

            if (!dishes.isEmpty()) {
                families.add(new Family(family, dishes));
            }
        }

        return new MenuResponse(families);
    }

    private Document getDocument() {
        try {
            return Jsoup.connect(this.url).get();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read " + e);
        }
    }



}
