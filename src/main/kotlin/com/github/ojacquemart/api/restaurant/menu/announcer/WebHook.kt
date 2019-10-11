package com.github.ojacquemart.api.restaurant.menu.announcer

import com.github.ojacquemart.api.restaurant.menu.io.WriterOption

data class WebHook(var url: String? = null,
                   var times: Int? = DEFAULT_TIMES,
                   var sleep: Long? = null,
                   var writerOptions: List<WriterOption> = emptyList(),
                   var textAttribute: String? = DEFAULT_TEXT_ATTRIBUTE,
                   var additionalAttributes: Map<String, String>? = null) {

    companion object {
        private const val DEFAULT_TIMES = 1
        private const val DEFAULT_TEXT_ATTRIBUTE = "text"
    }

    fun safeTimes() = times ?: DEFAULT_TIMES

    fun getPayload(menu: String) =
            mapOf((textAttribute to menu))
                    .plus(additionalAttributes ?: emptyMap())

}
