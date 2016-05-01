package com.github.ojacquemart.apiauchan.restaurant.menu;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DishTest {

    private Dish dish1 = new Dish("foo", "1€");
    private Dish dish2 = new Dish("bar", "2€");
    private Dish dish3 = new Dish("qix", "1€");

    @Test
    public void testCompare() throws Exception {
        assertThat(dish1.compareTo(dish2)).isLessThan(0);
        assertThat(dish1.compareTo(dish1)).isEqualTo(0);
        assertThat(dish2.compareTo(dish1)).isGreaterThan(0);
        assertThat(dish1.compareTo(dish3)).isLessThan(0);
    }

    @Test
    public void testToString() throws Exception {
        assertThat(dish1.toString()).isEqualTo("foo 1€");
    }

}