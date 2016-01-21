package com.github.ojacquemart.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MenuResponse {
    private List<Family> families;
}
