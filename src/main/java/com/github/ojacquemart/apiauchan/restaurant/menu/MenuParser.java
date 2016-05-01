package com.github.ojacquemart.apiauchan.restaurant.menu;

import com.github.ojacquemart.apiauchan.restaurant.util.jsoup.JsoupDocument;
import com.github.ojacquemart.apiauchan.restaurant.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class MenuParser {
    public static final String DISH_FAMILY_SELECTOR = ".dishes-list";
    public static final String DISH_NAME_SELECTOR = ".dish-name";
    public static final String DISH_PRICE_SELECTOR = ".dish-price";

    public static final String EURO = "€";
    public static final String EURO_EMOJI = ":euro:";

    public static Menu parseFromUrl() {
        return new MenuParser(JsoupDocument.url(Menu.URL)).parse();
    }

    private Document document;

    public Menu parse() {
        log.info("Read menus");

        return readMenu();
    }

    private Menu readMenu() {
        List<DishGroup> dishGroups = readDishGroups();

        return new Menu(dishGroups);
    }

    private List<DishGroup> readDishGroups() {
        log.debug("Read dish groups");

        List<DishGroup> groups = new ArrayList<>();

        Elements elements = document.select(DISH_FAMILY_SELECTOR);
        for (Element element : elements) {
            Elements subElements = element.select("li");
            String groupName = subElements.first().text();
            log.trace("Group: {}", groupName);

            List<Dish> dishes = readDishes(subElements);
            if (!dishes.isEmpty()) {
                groups.add(new DishGroup(groupName, dishes));
            } else {
                log.debug("{} is empty!", groupName);
            }
        }

        return groups;
    }

    private List<Dish> readDishes(Elements subElements) {
        List<Dish> dishes = new ArrayList<>();

        // start loop at 1, first element is an empty dish
        for (int i = 1; i < subElements.size(); i++) {
            Element dishElement = subElements.get(i);

            String dishName = dishElement.select(DISH_NAME_SELECTOR).text();
            String dishNameAsCapitalize = StringUtils.capitalize(dishName);
            String dishNameWithEuroEmoji = StringUtils.replace(dishNameAsCapitalize, EURO, EURO_EMOJI);

            String dishPrice = dishElement.select(DISH_PRICE_SELECTOR).text();

            Dish dish = new Dish(dishNameWithEuroEmoji, dishPrice);

            log.trace("\tDish: {}", dish);
            dishes.add(dish);
        }

        Collections.sort(dishes);

        return dishes;
    }

}
