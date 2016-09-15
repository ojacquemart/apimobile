package com.github.ojacquemart.apiauchan.restaurant.util.jsoup

import com.github.ojacquemart.apiauchan.restaurant.lang.loggerFor
import lombok.experimental.UtilityClass
import lombok.extern.slf4j.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.nio.charset.StandardCharsets

@Slf4j
@UtilityClass
object JsoupDocument {

    private val TIMEOUT = 10000

    private val log = loggerFor<JsoupDocument>()

    fun url(url: String): Document {
        try {
            log.debug("Read {} document", url)

            return Jsoup.connect(url).timeout(TIMEOUT).get()
        } catch (e: Exception) {
            throw JsoupDocumentReadException(url, e)
        }

    }

    fun file(fileName: String): Document {
        try {
            log.debug("Read {} document", fileName)

            val inputStream = JsoupDocument::class.java.javaClass.getResourceAsStream(fileName)

            return Jsoup.parse(inputStream, StandardCharsets.UTF_8.name(), "/")
        } catch (e: Exception) {
            throw JsoupDocumentReadException(fileName, e)
        }

    }

}
