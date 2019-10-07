package com.github.ojacquemart.api.restaurant.menu

data class Menu(val dishGroups: List<DishGroup>) {

    override fun toString(): String {
        val builder = StringBuilder()
                .append(ASTERISK).append(TITLE).append(ASTERISK)
                .append(NEW_LINE).append(NEW_LINE)

        for (dishGroup in dishGroups) {
            builder.append(PARAGRAPH).append(SPACE)
                    .append(ASTERISK).append(dishGroup.label).append(ASTERISK)
                    .append(NEW_LINE)

            for (dish in dishGroup.dishes) {
                builder.append(dish).append(NEW_LINE)
            }

            builder.append(NEW_LINE)
        }

        return builder.toString()
    }

    companion object {
        val TITLE = "API MENU"
        val NEW_LINE = "\n"
        val TAB = "\t"
        val ASTERISK = "*"
        val SPACE = " "
        val PARAGRAPH = ">"
    }

}