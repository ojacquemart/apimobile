package com.github.ojacquemart.apiauchan.restaurant.util.jsoup

class JsoupDocumentReadException(sourceCause: String, e: Exception) :
        RuntimeException("Failed to read document from " + sourceCause, e)
