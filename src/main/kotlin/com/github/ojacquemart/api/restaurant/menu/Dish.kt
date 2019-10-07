package com.github.ojacquemart.api.restaurant.menu

data class Dish(val name: String, val price: String) : Comparable<Dish> {

    override fun compareTo(other: Dish): Int {
        val priceCompareTo = price.compareTo(other.price)
        if (priceCompareTo != 0) {
            return priceCompareTo
        }

        return name.compareTo(other.name)
    }

    override fun toString(): String {
        return name + " " + price
    }

}