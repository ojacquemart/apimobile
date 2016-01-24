package com.github.ojacquemart.apiauchan.restaurant.menu.reader;

import com.github.ojacquemart.apiauchan.restaurant.jsoup.DocumentReader;
import com.github.ojacquemart.apiauchan.restaurant.menu.model.DishFamily;
import com.github.ojacquemart.apiauchan.restaurant.menu.model.MenuResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MenuReader {
    public static final String DISH_FAMILY_SELECTOR = ".dishes-list";
    public static final String url = "http://auchan1.apimobile.fr/LesMenus";

    private static final DocumentReader DOCUMENT_READER = new DocumentReader();

    public MenuResponse getMenu() {
        log.info("Read menus");

        return readMenu();
    }

    public String getMenuAsString() {
        return readMenu().toString();
    }

    private MenuResponse readMenu() {
        Document document = DOCUMENT_READER.getDocument(url);

        List<DishFamily> families = readDishFamilies(document);

        return new MenuResponse(families);
    }

    private List<DishFamily> readDishFamilies(Document document) {
        List<DishFamily> families = new ArrayList<>();

        Elements elements = document.select(DISH_FAMILY_SELECTOR);
        for (Element element : elements) {
            Elements subElements = element.select("li");
            String family = subElements.first().text();
            log.info("Family: {}", family);

            List<String> dishes = readDishes(subElements);
            if (!dishes.isEmpty()) {
                families.add(new DishFamily(family, dishes));
            } else {
                log.info("{} is empty!", family);
            }
        }
        return families;
    }

    private List<String> readDishes(Elements subElements) {
        List<String> dishes = new ArrayList<>();

        for (int i = 1; i < subElements.size(); i++) {
            String dish = subElements.get(i).text();
            log.info("\tDish: {}", dish);

            dishes.add(dish);
        }

        return dishes;
    }

}
