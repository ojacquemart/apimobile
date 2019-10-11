package com.github.ojacquemart.api.restaurant.menu

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class MenuController(val menuService: MenuService) {

    @RequestMapping(value = ["/menus"], method = [RequestMethod.GET], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun menusAsString(): String = menuService.getMenuAsString()

}