package com.github.ojacquemart.api.restaurant.menu

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("menu")
data class MenuProperties(var url: String? = null) {

    fun safeUrl() = url ?: throw InvalidConfigurationException("Invalid configuration")

}