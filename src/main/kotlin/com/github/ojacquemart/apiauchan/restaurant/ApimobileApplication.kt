package com.github.ojacquemart.apiauchan.restaurant

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ApiAuchanRestaurantMenusApplication

fun main(args: Array<String>) {
    SpringApplication.run(ApiAuchanRestaurantMenusApplication::class.java, *args)
}
