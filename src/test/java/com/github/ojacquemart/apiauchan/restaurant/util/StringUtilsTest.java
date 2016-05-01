package com.github.ojacquemart.apiauchan.restaurant.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    @Test
    public void testCapitalize() throws Exception {
        assertThat(StringUtils.capitalize(null)).isEqualTo(null);
        assertThat(StringUtils.capitalize("")).isEqualTo("");
        assertThat(StringUtils.capitalize("f")).isEqualTo("F");
        assertThat(StringUtils.capitalize("foo bar qix")).isEqualTo("Foo bar qix");
        assertThat(StringUtils.capitalize("foo Bar Qix")).isEqualTo("Foo bar qix");
    }

}