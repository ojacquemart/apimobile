package com.github.ojacquemart.api.restaurant.menu.announcer

data class AnnouncerProperties(var enabled: Boolean? = false,
                               var cron: String? = null,
                               var webHooks: List<WebHook>? = null)
