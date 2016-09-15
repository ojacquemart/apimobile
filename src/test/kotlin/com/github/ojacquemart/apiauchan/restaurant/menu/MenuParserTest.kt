package com.github.ojacquemart.apiauchan.restaurant.menu

import com.github.ojacquemart.apiauchan.restaurant.util.jsoup.JsoupDocument
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MenuParserTest {

    @Test
    @Throws(Exception::class)
    fun testParse() {
        val document = JsoupDocument.file("/20160429-api-restauration.html")

        val parser = MenuParser(document)
        val menu = parser.parse()

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