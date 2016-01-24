package com.github.ojacquemart.apiauchan.restaurant.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MenuResponse {
    public static final String TITLE = "API MENU";
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";

    private List<DishFamily> families;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(TITLE).append(NEW_LINE).append(NEW_LINE);

        for (DishFamily dishFamily : families) {
            builder.append(dishFamily.getLabel()).append(NEW_LINE);

            for (String dish : dishFamily.getDishes()) {
                builder.append(TAB).append(dish).append(NEW_LINE);
            }

            builder.append(NEW_LINE);
        }

        return builder.toString();
    }
}
