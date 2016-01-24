package com.github.ojacquemart.apiauchan.restaurant.menu.slack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageSender {
    private static final SlackClient CLIENT = new SlackClient();

    public void sendMenu(String message) {
        log.info("Message: {}", message);

        CLIENT.postSimpleMessage(message);
    }

}
