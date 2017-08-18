package com.keessi.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class OAuth2Controller {
    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
