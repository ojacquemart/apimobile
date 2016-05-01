package com.github.ojacquemart.apiauchan.restaurant.menu;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {

    private Menu menu;

    @Before
    public void setUp() throws Exception {
        menu = new Menu(Arrays.asList(
                new DishGroup("foo", Arrays.asList(new Dish("foo", "1€"), new Dish("bar", "2€"))),
                new DishGroup("bar", Arrays.asList(new Dish("bar", "3€"), new Dish("bar", "4€"))))
        );
    }

    @Test
    public void testToString() throws Exception {
        String menuAsString = menu.toString();

        assertThat(menuAsString).isNotNull();
        assertThat(menuAsString).isEqualTo(
            "API MENU\n\n" +
                    "foo\n" +
                    "\tfoo 1€\n" +
                    "\tbar 2€\n" +
                    "\n" +
                    "bar\n" +
                    "\tbar 3€\n" +
                    "\tbar 4€\n" +
                    "\n"
        );
    }
}