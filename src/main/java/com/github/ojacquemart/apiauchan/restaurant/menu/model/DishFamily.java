package com.github.ojacquemart.apiauchan.restaurant.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DishFamily {
    private String label;
    private List<String> dishes;
}
