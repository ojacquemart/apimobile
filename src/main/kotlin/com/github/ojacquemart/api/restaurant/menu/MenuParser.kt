package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.lang.loggerFor
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.stereotype.Service
import java.util.*

@Service
class MenuParser {

    fun parse(document: Document): Menu {
        log.info("Read menus")

        val dishGroups = readDishGroups(document)

        return Menu(dishGroups)
    }

    private fun readDishGroups(document: Document): List<DishGroup> {
        log.debug("Read dish groups")

        val groups = ArrayList<DishGroup>()

        val elements = document.select(DISH_FAMILY_SELECTOR)
        for (element in elements) {
            val subElements = element.select("li")
            val groupName = subElements.first().text()
            log.trace("Group: {}", groupName)

            val dishes = readDishes(subElements)
            if (!dishes.isEmpty()) {
                groups.add(DishGroup(groupName, dishes))
            } else {
                log.debug("{} is empty!", groupName)
            }
        }

        return groups
    }

    private fun readDishes(subElements: Elements): List<Dish> {
        val dishes = ArrayList<Dish>()

        // start loop at 1, first element is an empty dish
        for (i in 1..subElements.size - 1) {
            val dishElement = subElements[i]

            val dishName = dishElement.select(DISH_NAME_SELECTOR).text()
            val dishNameAsCapitalize = dishName.capitalize()
            val dishNameWithEuroEmoji = dishNameAsCapitalize.replace(EURO, EURO_EMOJI)

            val dishPrice = dishElement.select(DISH_PRICE_SELECTOR).text()

            val dish = Dish(dishNameWithEuroEmoji, dishPrice)

            log.trace("\tDish: {}", dish)
            dishes.add(dish)
        }

        Collections.sort(dishes)

        return dishes
    }

    companion object {
        private val log = loggerFor<MenuParser>()

        val DISH_FAMILY_SELECTOR = ".dishes-list"
        val DISH_NAME_SELECTOR = ".dish-name"
        val DISH_PRICE_SELECTOR = ".dish-price"

        val EURO = "€"
        val EURO_EMOJI = ":euro:"
    }

}