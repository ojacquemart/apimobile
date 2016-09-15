package com.github.ojacquemart.apiauchan.restaurant.menu

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/apiauchan")
@RestController
class MenuController {

    @RequestMapping(value = "/menus", method = arrayOf(RequestMethod.GET), produces = arrayOf(MediaType.TEXT_PLAIN_VALUE))
    fun menusAsString(): String = MenuParser.parseFromUrl()

}