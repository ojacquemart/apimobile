package com.github.ojacquemart.apiauchan.restaurant.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentReader {

    public static final int TIMEOUT = 10000;

    public Document getDocument(String url) {
        try {
            return Jsoup.connect(url).timeout(TIMEOUT).get();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read document from " + url, e);
        }
    }

}
