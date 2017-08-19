package com.keessi.web.secure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keessi.web.entity.Flight;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Base64;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OAuthApplicationTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy filterChain;
    private MockMvc mvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).addFilters(this.filterChain).build();
        SecurityContextHolder.clearContext();
    }

    @Test
    @Ignore
    public void everythingIsSecuredByDefault() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaTypes.HAL_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
        this.mvc.perform(MockMvcRequestBuilders.get("flights").accept(MediaTypes.HAL_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
        this.mvc.perform(MockMvcRequestBuilders.get("/flights/1").accept(MediaTypes.HAL_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
        this.mvc.perform(MockMvcRequestBuilders.get("/alps").accept(MediaTypes.HAL_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void accessingRootUriPossibleWithUserAccount() throws Exception {
        String header = "Basic " +
                new String(Base64.getEncoder().encode("spc:199602".getBytes()));
        this.mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaTypes.HAL_JSON).header("Authorization", header))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", MediaTypes.HAL_JSON.toString() + ";charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void useAppSecretsPlusUserAccountToGetBearerToken() throws Exception {
        String header = "Basic " +
                new String(Base64.getEncoder().encode("foo:bar".getBytes()));
        MvcResult result = this.mvc
                .perform(MockMvcRequestBuilders.post("/oauth/token")
                        .header("Authorization", header)
                        .param("grant_type", "password")
                        .param("scope", "read")
                        .param("username", "spc")
                        .param("password", "199602"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Object accessToken = this.objectMapper
                .readValue(result.getResponse().getContentAsString(), Map.class)
                .get("access_token");
        MvcResult flightAction = this.mvc
                .perform(MockMvcRequestBuilders.get("/flights/1").accept(MediaTypes.HAL_JSON).header("Authorization", "Bearer " + accessToken))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", MediaTypes.HAL_JSON.toString() + ";charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Flight flight = this.objectMapper.readValue(
                flightAction.getResponse().getContentAsString(), Flight.class);
        Assertions.assertThat(flight.getOrigin()).isEqualTo("Changsha");
        Assertions.assertThat(flight.getDestination()).isEqualTo("Wuhan");
        Assertions.assertThat(flight.getAirline()).isEqualTo("CW");
        Assertions.assertThat(flight.getFlightNumber()).isEqualTo("CW-001");
        Assertions.assertThat(flight.getTraveler()).isEqualTo("spc");
    }
}
