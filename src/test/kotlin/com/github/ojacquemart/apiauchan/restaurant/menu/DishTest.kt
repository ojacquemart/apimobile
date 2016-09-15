package com.github.ojacquemart.apiauchan.restaurant.menu

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DishTest {

    private val dish1 = Dish("foo", "1€")
    private val dish2 = Dish("bar", "2€")
    private val dish3 = Dish("qix", "1€")

    @Test
    @Throws(Exception::class)
    fun testCompare() {
        assertThat(dish1.compareTo(dish2)).isLessThan(0)
        assertThat(dish1.compareTo(dish1)).isEqualTo(0)
        assertThat(dish2.compareTo(dish1)).isGreaterThan(0)
        assertThat(dish1.compareTo(dish3)).isLessThan(0)
    }

    @Test
    @Throws(Exception::class)
    fun testToString() {
        assertThat(dish1.toString()).isEqualTo("foo 1€")
    }

}