package com.github.ojacquemart.apiauchan.restaurant.menu;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/apiauchan")
@RestController
public class MenuController {

    @RequestMapping(value = "/menus", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String getMenusAsString() {
        return MenuParser.parseFromUrl().toString();
    }

}
