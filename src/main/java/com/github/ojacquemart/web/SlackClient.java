package com.github.ojacquemart.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SlackClient {

    private final RestTemplate template = new JacksonRestTemplate();
    private SlackProperties properties = new SlackProperties();

    public void postSimpleMessage(String message) {
        BasicMessage basicMessage = new BasicMessage(properties, message);
        postMessage(basicMessage);
    }

    private void postMessage(BasicMessage slackWebhook) {
        try {
            template.postForObject(slackWebhook.getUrl(), slackWebhook, String.class);
        } catch (RestClientException e) {
            log.error("Failed to send the webhook message: {}", slackWebhook, e);
        }
    }
}
