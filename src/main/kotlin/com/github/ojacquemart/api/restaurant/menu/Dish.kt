package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.menu.io.Writeable
import com.github.ojacquemart.api.restaurant.menu.io.WriterOption

data class Dish(val name: String, val price: String) : Comparable<Dish>, Writeable {

    override fun compareTo(other: Dish): Int {
        val priceCompareTo = price.compareTo(other.price)
        if (priceCompareTo != 0) {
            return priceCompareTo
        }

        return name.compareTo(other.name)
    }

    override fun write(options: List<WriterOption>): String {
        if (options.contains(WriterOption.DISH_PRICE)) {
            return "$name *($price)*"
        }

        return name
    }

}