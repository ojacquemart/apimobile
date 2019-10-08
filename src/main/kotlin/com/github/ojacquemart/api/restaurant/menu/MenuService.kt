package com.github.ojacquemart.api.restaurant.menu

import org.springframework.stereotype.Service

@Service
class MenuService(val properties: MenuProperties) {

    fun getMenu() = MenuParser.parseFromUrl(properties.safeUrl())

}