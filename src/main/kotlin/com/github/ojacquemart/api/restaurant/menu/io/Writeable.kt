package com.github.ojacquemart.api.restaurant.menu.io

interface Writeable {

    fun write(options: List<WriterOption> = listOf(WriterOption.TITLE, WriterOption.DISH_PRICE)): String

}
