package com.github.ojacquemart.apiauchan.restaurant.menu;

import com.github.ojacquemart.apiauchan.restaurant.menu.reader.MenuReader;
import com.github.ojacquemart.apiauchan.restaurant.menu.slack.MessageSender;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/apiauchan")
@RestController
public class MenuController {

    private MessageSender sender;
    private MenuReader reader;

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    @Scheduled(cron = "0 0 10 * * 1-5")
    public void postMenus() {
        String menu = reader.getMenuAsString();
        sender.sendMenu(menu);
    }

    @RequestMapping(value = "/menus", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String getMenusAsString() {
        return reader.getMenuAsString();
    }

}
