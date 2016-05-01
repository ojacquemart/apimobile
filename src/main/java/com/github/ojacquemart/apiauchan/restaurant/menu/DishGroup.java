package com.github.ojacquemart.apiauchan.restaurant.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DishGroup {
    private String label;
    private List<Dish> dishes;
}
