package com.github.ojacquemart.apiauchan.restaurant.util;

public class StringUtils {

    public static String capitalize(String inString) {
        if (inString == null || inString.isEmpty()) {
            return inString;
        }

        return inString.substring(0, 1).toUpperCase() + inString.substring(1).toLowerCase();
    }

    public static String replace(String inString, String oldPattern, String newPattern) {
        return org.springframework.util.StringUtils.replace(inString, oldPattern, newPattern);
    }

}
