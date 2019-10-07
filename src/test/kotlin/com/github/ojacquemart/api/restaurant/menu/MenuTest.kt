package com.github.ojacquemart.api.restaurant.menu

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class MenuTest {

    private var menu: Menu? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        menu = Menu(listOf(
                DishGroup("foo", listOf(Dish("foo", "1€"), Dish("bar", "2€"))),
                DishGroup("bar", listOf(Dish("bar", "3€"), Dish("bar", "4€")))))
    }

    @Test
    @Throws(Exception::class)
    fun testToString() {
        val menuAsString = menu!!.toString()

        assertThat(menuAsString).isNotNull()
        assertThat(menuAsString).isEqualTo(
                "*API MENU*\n\n" +
                        "> *foo*\n" +
                        "foo 1€\n" +
                        "bar 2€\n" +
                        "\n" +
                        "> *bar*\n" +
                        "bar 3€\n" +
                        "bar 4€\n" +
                        "\n")
    }
}