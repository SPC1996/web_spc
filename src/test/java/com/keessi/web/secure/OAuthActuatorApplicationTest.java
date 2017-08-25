package com.keessi.web.secure;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OAuthActuatorApplicationTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy filterChain;
    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(this.context).addFilters(this.filterChain).build();
        SecurityContextHolder.clearContext();
    }

    @Test
    public void healthWithBasicAuthorization() throws Exception {
        this.mvc.perform(get("/health")
                .header("Authorization", "Basic " + Base64Utils.encodeToString("spc:199602".getBytes())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void envSecured() throws Exception {
        this.mvc.perform(get("/env"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void envWithBasicAuthorization() throws Exception {
        this.mvc.perform(get("/env")
                .header("Authorization", "Basic " + Base64Utils.encodeToString("spc:199602".getBytes())))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
