package com.github.ojacquemart.api.restaurant.menu.announcer

import com.github.ojacquemart.api.restaurant.lang.loggerFor
import com.github.ojacquemart.api.restaurant.menu.MenuProperties
import com.github.ojacquemart.api.restaurant.menu.MenuService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Thread.sleep
import javax.annotation.PostConstruct

@Service
@ConditionalOnProperty(value = ["menu.announcer.enabled"], havingValue = "true")
class AnnouncerService(val template: RestTemplate,
                       val properties: MenuProperties,
                       val menuService: MenuService) {

    private val logger = loggerFor<AnnouncerService>()

    @PostConstruct
    fun init() {
        logger.debug("Announcer configured [cron=${properties.announcer?.cron}]")
    }

    @Scheduled(cron = "\${menu.announcer.cron}")
    fun announce() {
        properties.announcer?.webHooks?.let {
            doAnnounce(it)
        } ?: logger.warn("No web hooks configured?")
    }

    private fun doAnnounce(webHooks: List<WebHook>) {
        val menu = menuService.getMenu()

        logger.debug("${webHooks.size} web hook(s) to announce")
        webHooks.forEach {
            postAnnounce(it, menu)
        }
    }

    private fun postAnnounce(webHook: WebHook, menu: String) {
        logger.debug("Post menu to $webHook")

        repeat(webHook.safeTimes()) {
            postMenu(webHook, menu)
            sleepIfNecessary(webHook)
        }
    }

    private fun postMenu(webHook: WebHook, menu: String) {
        webHook.url?.let { url ->
            val payload = webHook.getPayload(menu)

            template.postForEntity(url, payload, Void::class.java)
        } ?: logger.warn("No web hook url defined")
    }

    private fun sleepIfNecessary(webHook: WebHook) {
        webHook.sleep?.let {
            sleep(it)
        }
    }

}
