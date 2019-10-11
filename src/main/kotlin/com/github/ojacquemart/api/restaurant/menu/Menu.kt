package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.menu.io.Writeable
import com.github.ojacquemart.api.restaurant.menu.io.WriterOption

data class Menu(val dishGroups: List<DishGroup>,
                val yesterdayMenu: Menu? = null) : Writeable {

    override fun write(options: List<WriterOption>): String {
        val builder = StringBuilder()

        if (options.contains(WriterOption.TITLE)) {
            builder.append(TITLE_ASTERISK).append(TITLE).append(TITLE_ASTERISK)
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

        concatYesterdayMenuIfNecessary(builder, options)

        return builder.toString()
    }

    private fun concatYesterdayMenuIfNecessary(builder: StringBuilder, options: List<WriterOption>) {
        if (hasYesterdayMenu(options)) {
            builder.append(TITLE_ASTERISK).append(YESTERDAY).append(TITLE_ASTERISK)
                    .append(NEW_LINE).append(NEW_LINE)
                    .append(yesterdayMenu?.write(options.filter { it != WriterOption.TITLE }))
        }
    }

    private fun hasYesterdayMenu(options: List<WriterOption>) = options.contains(WriterOption.YESTERDAY)
            && yesterdayMenu != null

    companion object {
        val TITLE = "API MENU"
        // TODO: i18n?
        val YESTERDAY = "Menu de la veille :thinking:"
        val NEW_LINE = "\n"
        val TITLE_ASTERISK = "**"
        val ASTERISK = "*"
        val SPACE = " "
        val PARAGRAPH = ">"
    }

}