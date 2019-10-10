package com.github.ojacquemart.api.restaurant.menu.announcer

import com.github.ojacquemart.api.restaurant.lang.loggerFor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Text(val text: String? = null)

@RequestMapping("/hello")
@RestController
class HelloController {

    private val logger = loggerFor<HelloController>()

    @PostMapping
    fun hello(@RequestBody text: Text) {
        logger.debug("Text received: $text")
    }

}
