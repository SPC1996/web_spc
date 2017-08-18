package com.keessi.web.secure;

import com.keessi.web.Application;
import com.keessi.web.service.SecureService;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class SecureApplicationTest {
    @Autowired
    private SecureService service;

    @Autowired
    private ApplicationContext context;

    private Authentication authentication;

    @Before
    public void init() {
        AuthenticationManager manager = this.context
                .getBean(AuthenticationManager.class);
        this.authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken("spc", "199602")
        );
    }

    @After
    public void close() {
        SecurityContextHolder.clearContext();
    }

    @Test(expected = AuthenticationException.class)
    public void secure() throws Exception {
        Assertions.assertThat("Hello Security").isEqualTo(this.service.secure());
    }

    @Test
    public void authenticated() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        Assertions.assertThat("Hello Security").isEqualTo(this.service.secure());
    }

    @Test
    public void preauth() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        Assertions.assertThat("Hello World").isEqualTo(this.service.authorized());
    }

    @Test(expected = AccessDeniedException.class)
    public void denied() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        Assertions.assertThat("Goodbye World").isEqualTo(this.service.denied());
    }
}
