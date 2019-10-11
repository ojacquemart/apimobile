package com.github.ojacquemart.api.restaurant.menu

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class MenuTest {

    private lateinit var menu: Menu

    @Before
    @Throws(Exception::class)
    fun setUp() {
        menu = Menu(listOf(
                DishGroup("foo", listOf(Dish("foo", "1€"), Dish("bar", "2€"))),
                DishGroup("bar", listOf(Dish("bar", "3€"), Dish("bar", "4€")))))
    }

    @Test
    fun testWrite() {
        val menuAsString = menu.write()

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

    @Test
    fun testWriteWithEmptyOptions() {
        val menuAsString = menu.write(emptyList())

        assertThat(menuAsString).isNotNull()
        assertThat(menuAsString).isEqualTo(
                        "> *foo*\n" +
                        "foo\n" +
                        "bar\n" +
                        "\n" +
                        "> *bar*\n" +
                        "bar\n" +
                        "bar\n" +
                        "\n")
    }

    @Test
    fun testWriteWithOnlyOneDishGroup() {
        val menu =  Menu(listOf(
                DishGroup("foo", listOf(Dish("foo", "1€"), Dish("bar", "2€")))))
        val menuAsString = menu.write(emptyList())

        assertThat(menuAsString).isNotNull()
        assertThat(menuAsString).isEqualTo(
                        "foo\n" +
                        "bar\n" +
                        "\n")
    }

}