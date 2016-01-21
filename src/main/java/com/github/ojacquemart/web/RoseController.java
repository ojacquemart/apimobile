package com.github.ojacquemart.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/rose")
@RestController
public class RoseController {

    private MenuSender sender;

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public void getMenus() {
        sender.sendMenu();
    }

}
