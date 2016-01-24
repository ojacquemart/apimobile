package com.github.ojacquemart.apiauchan.restaurant.menu.slack;

import com.github.ojacquemart.apiauchan.restaurant.web.JacksonRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SlackClient {

    private static final RestTemplate JACKSON_REST_TEMPLATE = new JacksonRestTemplate();
    private static final SlackProperties SLACK_PROPERTIES = new SlackProperties();

    public void postSimpleMessage(String message) {
        BasicMessage basicMessage = new BasicMessage(SLACK_PROPERTIES, message);
        postMessage(basicMessage);
    }

    private void postMessage(BasicMessage slackWebhook) {
        try {
            JACKSON_REST_TEMPLATE.postForObject(slackWebhook.getUrl(), slackWebhook, String.class);
        } catch (RestClientException e) {
            log.error("Failed to send the incoming webhook message: {}", slackWebhook, e);
        }
    }
}
