package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.lang.loggerFor
import com.github.ojacquemart.api.restaurant.util.jsoup.JsoupDocument
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MenuService(val properties: MenuProperties) {

    companion object {
        private const val MENU_FETCH_ERROR = "Oops, I swallowed an hard to digest error... maybe the site is down! Retry the command... :rose:"
    }

    private val logger = loggerFor<MenuService>()

    fun getMenuAsString(): String = try {
        val menu = getMenu()

        menu.write()
    } catch (e: Exception) {
        MENU_FETCH_ERROR
    }

    @Cacheable(value = ["menus"])
    fun getMenu(): Menu = try {
        MenuParser(JsoupDocument.url(properties.safeUrl())).parse()
    } catch (e: Exception) {
        logger.error("Error while reading the menu")

        throw e
    }

}