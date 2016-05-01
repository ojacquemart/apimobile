package com.github.ojacquemart.apiauchan.restaurant.menu;

import com.github.ojacquemart.apiauchan.restaurant.util.jsoup.JsoupDocument;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuParserTest {

    private MenuParser parser;

    @Before
    public void setUp() throws Exception {
        Document document = JsoupDocument.file("/20160429-api-restauration.html");

        parser = new MenuParser(document);
    }

    @Test
    public void testParse() throws Exception {
        Menu menu = parser.parse();

        assertThat(menu).isNotNull();
        List<DishGroup> dishGroups = menu.getDishGroups();
        assertThat(dishGroups).hasSize(5);

        DishGroup dishGroup1 = dishGroups.get(0);
        assertThat(dishGroup1.getLabel()).isEqualTo("Pâte & Pâtes");

        List<Dish> dishes = dishGroup1.getDishes();
        assertThat(dishes).hasSize(2)
            .extracting("name")
            .contains("Demie faluche à la bolognaise €", "Randolini brasato");
    }

}