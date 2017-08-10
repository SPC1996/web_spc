package com.keessi.web.controller;

import com.keessi.web.entity.Greeting;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {
    @LocalServerPort
    private int port;
    private URL base;
    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getGreetingNoParam() throws Exception {
        ResponseEntity<Greeting> response=template.getForEntity(base.toString()+"greeting", Greeting.class);
        Assert.assertEquals("Hello, World!", response.getBody().getContent());
    }

    @Test
    public void getGreetingWithParam() throws Exception {
        ResponseEntity<Greeting> response=template.getForEntity(base.toString()+"greeting?name=spc", Greeting.class);
        Assert.assertEquals("Hello, spc!", response.getBody().getContent());
    }
}
