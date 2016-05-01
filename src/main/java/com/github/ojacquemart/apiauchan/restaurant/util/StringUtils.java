package com.github.ojacquemart.apiauchan.restaurant.util;

public class StringUtils {

    public static String capitalize(String words) {
        if (words == null || words.isEmpty()) {
            return words;
        }

        return words.substring(0, 1).toUpperCase() + words.substring(1).toLowerCase();
    }

}
