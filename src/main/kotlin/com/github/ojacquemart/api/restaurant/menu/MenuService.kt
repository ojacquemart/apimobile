package com.github.ojacquemart.api.restaurant.menu

import com.github.ojacquemart.api.restaurant.lang.loggerFor
import com.github.ojacquemart.api.restaurant.util.jsoup.JsoupDocument
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class MenuService(val properties: MenuProperties,
                  val menuParser: MenuParser) {

    companion object {
        private const val MENU_FETCH_ERROR = "Oops, I swallowed an hard to digest error... maybe the site is down! Retry the command... :rose:"
        private const val MENU_YESTERDAY_DATE_FORMAT = "dd-MM-YYYY"
    }

    private val logger = loggerFor<MenuService>()

    fun getMenuAsString(): String = try {
        val menu = getMenu()

        menu.write()
    } catch (e: Exception) {
        MENU_FETCH_ERROR
    }

    @Cacheable("menus")
    fun getMenu(): Menu = try {
        val todayMenu = getTodayMenu()
        val yesterdayMenu = getYesterdayMenu()

        Menu(todayMenu.dishGroups, yesterdayMenu = yesterdayMenu)
    } catch (e: Exception) {
        logger.error("Error while reading the menu")

        throw e
    }

    private fun getTodayMenu() = getMenu(properties.safeUrl())

    fun getYesterdayMenu(): Menu? {
        if (shouldReadYesterdayMenu()) {
            val yesterdayDate = getYesterdayDate()
            val url = properties.safeUrl()

            return getMenu("$url?date=$yesterdayDate")
        }

        return null
    }

    private fun shouldReadYesterdayMenu(): Boolean {
        val nowDayOfWeek = LocalDate.now().dayOfWeek.value

        return nowDayOfWeek > DayOfWeek.MONDAY.value && nowDayOfWeek < DayOfWeek.SATURDAY.value
    }

    private fun getYesterdayDate(): String? {
        return LocalDate.now().minusDays(1)
                .format(DateTimeFormatter.ofPattern(MENU_YESTERDAY_DATE_FORMAT))
    }

    private fun getMenu(url: String) = menuParser.parse(JsoupDocument.url(url))

}