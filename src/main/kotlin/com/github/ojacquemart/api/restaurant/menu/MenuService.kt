package com.github.ojacquemart.api.restaurant.menu

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
open class MenuService(val properties: MenuProperties) {

    @Cacheable(value = ["menus"])
    open fun getMenu(): String {
        return MenuParser.parseFromUrl(properties.safeUrl())
    }

}