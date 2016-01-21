package com.github.ojacquemart.web;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SlackProperties {
    private String webHookUrl = "https://hooks.slack.com/services/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";
    private String channel = "#bot";
    private String userName = "rose";
    private String iconEmoji = ":lindsay:";
}
