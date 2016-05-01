package com.github.ojacquemart.apiauchan.restaurant.menu;

import com.github.ojacquemart.apiauchan.restaurant.util.jsoup.JsoupDocument;
import com.github.ojacquemart.apiauchan.restaurant.util.jsoup.JsoupDocumentReadException;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JsoupDocument.class)
public class MenuParserTest {

    @Test
    public void testParse_onError() {
        PowerMockito.mockStatic(JsoupDocument.class);
        BDDMockito.given(JsoupDocument.url(anyString())).willThrow(JsoupDocumentReadException.class);

        String messageOnError = MenuParser.parseFromUrl();
        assertThat(messageOnError).startsWith("Oops");

        PowerMockito.verifyStatic();
    }

    @Test
    public void testParse() throws Exception {
        Document document = JsoupDocument.file("/20160429-api-restauration.html");

        MenuParser parser = new MenuParser(document);
        Menu menu = parser.parse();

        assertThat(menu).isNotNull();
        List<DishGroup> dishGroups = menu.getDishGroups();
        assertThat(dishGroups).hasSize(5);

        DishGroup dishGroup1 = dishGroups.get(0);
        assertThat(dishGroup1.getLabel()).isEqualTo("Pâte & Pâtes");

        List<Dish> dishes = dishGroup1.getDishes();
        assertThat(dishes).hasSize(2)
            .extracting("name")
            .contains("Demie faluche à la bolognaise :euro:", "Randolini brasato");
    }

}