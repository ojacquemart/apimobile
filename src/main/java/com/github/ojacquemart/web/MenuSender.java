package com.github.ojacquemart.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class MenuSender {
    public static final String NEW_LINE = "\n";
    private static final SlackClient CLIENT = new SlackClient();

    private MenuReader reader;

    @Scheduled(cron="0 10 * * 1,2,3,4,5")
    public void sendMenu() {
        StringBuilder builder = new StringBuilder("API MENU").append(NEW_LINE).append(NEW_LINE);

        MenuResponse menu = reader.getMenus();
        for (Family family : menu.getFamilies()) {
            builder.append(family.getLabel()).append(NEW_LINE);

            for (String dish : family.getDishes()) {
                builder.append("\t").append(dish).append(NEW_LINE);
            }

            builder.append(NEW_LINE);
        }

        String message = builder.toString();
        log.info("Message: {}", message);

        CLIENT.postSimpleMessage(message);
    }

}
