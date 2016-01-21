package com.github.ojacquemart.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Family {
    private String label;
    private List<String> dishes;
}
