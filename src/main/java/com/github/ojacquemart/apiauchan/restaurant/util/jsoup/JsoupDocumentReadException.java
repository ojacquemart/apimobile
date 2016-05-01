package com.github.ojacquemart.apiauchan.restaurant.util.jsoup;

public class JsoupDocumentReadException extends RuntimeException {

    public JsoupDocumentReadException(String sourceCause, Exception e) {
        super("Failed to read document from " + sourceCause, e);
    }

}
