package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, String> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("one", String.valueOf(1));
        model.put("two", String.valueOf(2));
        return "index";
    }
}
