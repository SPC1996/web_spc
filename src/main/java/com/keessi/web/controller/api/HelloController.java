package com.keessi.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    @RequestMapping(value = "")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}