package com.github.ojacquemart.api.restaurant

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling

@EnableCaching
@EnableConfigurationProperties
@EnableScheduling
@SpringBootApplication
open class ApiMobileApplication

fun main(args: Array<String>) {
    SpringApplication.run(ApiMobileApplication::class.java, *args)
}
