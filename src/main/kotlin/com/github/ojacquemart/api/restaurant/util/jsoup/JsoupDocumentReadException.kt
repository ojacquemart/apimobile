package com.github.ojacquemart.api.restaurant.util.jsoup

class JsoupDocumentReadException(sourceCause: String, e: Exception) :
        RuntimeException("Failed to read document from " + sourceCause, e)
