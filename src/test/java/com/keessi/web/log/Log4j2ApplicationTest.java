package com.keessi.web.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Base64Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Log4j2ApplicationTest {
    private static final Logger logger = LogManager.getLogger(Log4j2ApplicationTest.class);

    @Rule
    public OutputCapture output=new OutputCapture();

    @Autowired
    private MockMvc mvc;

    @Test
    public void testLogger() {
        logger.info("Hello World");
        this.output.expect(Matchers.containsString("Hello World"));
    }

    @Test
    public void validateLoggerEndpoint() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/loggers/org.apache.coyote.http11.Http11NioProtocol")
                .header("Authorization", "Basic " + Base64Utils.encodeToString("spc:199602".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        Matchers.equalTo("{\"configuredLevel\":\"WARN\",\"effectiveLevel\":\"WARN\"}"))
                );
    }
}
