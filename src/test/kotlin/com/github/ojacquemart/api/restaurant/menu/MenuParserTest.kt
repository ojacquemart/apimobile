package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.util.jsoup.JsoupDocument
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MenuParserTest {

    @Test
    fun testParse() {
        val document = JsoupDocument.file("/20160429-api-restauration.html")

        val parser = MenuParser()
        val menu = parser.parse(document)

        assertThat(menu).isNotNull()
        val dishGroups = menu.dishGroups
        assertThat(dishGroups).hasSize(5)

        val dishGroup1 = dishGroups[0]
        assertThat(dishGroup1.label).isEqualTo("Pâte & Pâtes")

        val dishes = dishGroup1.dishes
        assertThat(dishes).hasSize(2)
        assertThat(dishes[0].name).isEqualTo("Demie Faluche à la Bolognaise :euro:")
        assertThat(dishes[1].name).isEqualTo("Randolini brasato")
    }

}