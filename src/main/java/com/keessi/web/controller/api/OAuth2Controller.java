package com.keessi.web.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class OAuth2Controller {
    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
