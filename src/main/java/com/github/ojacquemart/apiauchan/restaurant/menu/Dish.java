package com.github.ojacquemart.apiauchan.restaurant.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Dish implements Comparable<Dish> {
    private String name;
    private String price;

    @Override
    public int compareTo(Dish dishCompareTo) {
        int priceCompareTo = price.compareTo(dishCompareTo.getPrice());
        if (priceCompareTo != 0) {
            return priceCompareTo;
        }

        return name.compareTo(dishCompareTo.getName());
    }

    @Override
    public String toString() {
        return name + " " + price;
    }

}
