package com.github.ojacquemart.apiauchan.restaurant.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Menu {
    public static final String URL = "http://auchan1.apimobile.fr/LesMenus";

    public static final String TITLE = "API MENU";
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";

    private List<DishGroup> dishGroups;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(TITLE).append(NEW_LINE).append(NEW_LINE);

        for (DishGroup dishGroup : dishGroups) {
            builder.append(dishGroup.getLabel()).append(NEW_LINE);

            for (Dish dish : dishGroup.getDishes()) {
                builder.append(TAB).append(dish).append(NEW_LINE);
            }

            builder.append(NEW_LINE);
        }

        return builder.toString();
    }

}
