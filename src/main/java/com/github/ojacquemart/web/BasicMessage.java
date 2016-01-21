package com.github.ojacquemart.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasicMessage {
    private String url;
    private String channel;
    private String username;
    private String text;
    @JsonProperty("icon_emoji")
    private String iconEmoji;

    public BasicMessage(SlackProperties slackProperties, String text) {
        this.url = slackProperties.getWebHookUrl();
        this.channel = slackProperties.getChannel();
        this.iconEmoji = slackProperties.getIconEmoji();
        this.username = slackProperties.getUserName();
        this.text = text;
    }
}
