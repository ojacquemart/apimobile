package com.github.ojacquemart.api.restaurant.menu.rest

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RestTemplateConfig {

    @Bean
    fun bean(builder: RestTemplateBuilder): RestTemplate {
        val template = builder.build()
        template.interceptors = listOf(ContentTypeJsonRequestInterceptor())

        return template
    }

}
