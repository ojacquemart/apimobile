package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.menu.io.Writeable
import com.github.ojacquemart.api.restaurant.menu.io.WriterOption

data class Menu(val dishGroups: List<DishGroup>): Writeable {

    override fun write(options: List<WriterOption>): String {
        val builder = StringBuilder()

        if (options.contains(WriterOption.TITLE)) {
            builder.append(ASTERISK).append(TITLE).append(ASTERISK)
                    .append(NEW_LINE).append(NEW_LINE)
        }

        val hashManyGroups = dishGroups.size > 1
        for (dishGroup in dishGroups) {
            if (hashManyGroups) {
                builder.append(PARAGRAPH).append(SPACE)
                        .append(ASTERISK).append(dishGroup.label).append(ASTERISK)
                        .append(NEW_LINE)
            }

            for (dish in dishGroup.dishes) {
                builder.append(dish.write(options)).append(NEW_LINE)
            }

            builder.append(NEW_LINE)
        }

        return builder.toString()
    }

    companion object {
        val TITLE = "API MENU"
        val NEW_LINE = "\n"
        val ASTERISK = "*"
        val SPACE = " "
        val PARAGRAPH = ">"
    }

}