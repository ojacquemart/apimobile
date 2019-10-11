package com.github.ojacquemart.api.restaurant.menu.io

interface Writeable {

    fun write(options: List<WriterOption> = WriterOption.values().asList()): String

}
