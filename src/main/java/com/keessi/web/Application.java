package com.keessi.web;

import com.keessi.web.service.SecureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;


@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Application implements CommandLineRunner {
    private final SecureService service;

    @Autowired
    public Application(SecureService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken("user", "N/A",
                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")));
        try {
            System.out.println(this.service.secure());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}
