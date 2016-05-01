package com.github.ojacquemart.apiauchan.restaurant.util.jsoup;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class JsoupDocument {

    private static final int TIMEOUT = 20000;

    public static Document url(String url) {
        try {
            log.debug("Read {} document", url);

            return Jsoup.connect(url).timeout(TIMEOUT).get();
        } catch (Exception e) {
            throw new JsoupDocumentReadException(url, e);
        }
    }

    public static Document file(String fileName) {
        try {
            log.debug("Read {} document", fileName);

            InputStream inputStream = JsoupDocument.class.getClass().getResourceAsStream(fileName);

            return Jsoup.parse(inputStream, StandardCharsets.UTF_8.name(), "/");
        } catch (Exception e) {
            throw new JsoupDocumentReadException(fileName, e);
        }
    }

}
