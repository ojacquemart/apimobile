package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.lang.loggerFor
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class MenuParser {

    fun parse(document: Document): Menu {
        log.info("Read menus")

        val dishGroups = readDishGroups(document)

        return Menu(dishGroups)
    }

    private fun readDishGroups(document: Document): List<DishGroup> {
        log.debug("Read dish groups")

        val elements = document.select(DISH_FAMILY_SELECTOR)

        return elements
                .mapNotNull { element ->
                    val subElements = element.select("li")
                    val groupName = subElements.first().text()
                    log.trace("Group: {}", groupName)

                    val dishes = readDishes(subElements)

                    when (dishes.isNotEmpty()) {
                        true -> DishGroup(groupName, dishes)
                        else -> {
                            log.debug("{} is empty!", groupName)

                            null
                        }
                    }
                }
    }

    private fun readDishes(subElements: Elements): List<Dish> {
        return subElements
                // skip the first element which is empty
                .drop(1)
                .map { dishElement ->
                    val dishName = dishElement.select(DISH_NAME_SELECTOR).text()
                    val dishNameAsCapitalize = dishName.capitalize()
                    val dishNameWithEuroEmoji = dishNameAsCapitalize.replace(EURO, EURO_EMOJI)

                    val dishPrice = dishElement.select(DISH_PRICE_SELECTOR).text()

                    val dish = Dish(dishNameWithEuroEmoji, dishPrice)
                    log.trace("\tDish: {}", dish)

                    dish
                }
                .sorted()
    }

    companion object {
        private val log = loggerFor<MenuParser>()

        const val DISH_FAMILY_SELECTOR = ".dishes-list"
        const val DISH_NAME_SELECTOR = ".dish-name"
        const val DISH_PRICE_SELECTOR = ".dish-price"

        const val EURO = "€"
        const val EURO_EMOJI = ":euro:"
    }

}