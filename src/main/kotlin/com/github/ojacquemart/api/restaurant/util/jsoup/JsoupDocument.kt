package com.github.ojacquemart.api.restaurant.util.jsoup

import com.github.ojacquemart.api.restaurant.lang.loggerFor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.nio.charset.StandardCharsets

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
