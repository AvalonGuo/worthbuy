package com.shop.worth2buy.controllerTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.logging.Logger;

/**
 * @author changqing
 */
@SpringBootTest
public class loginModuleTests {
    private final Logger logger = Logger.getLogger("loginModuleTests");
    MockMvc mockMvc;
    @BeforeEach
    void setUp(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void getCodeTest() throws Exception {
        String url = "http://localhost:8080/loginModule/codeGet";
        MvcResult result = this.mockMvc.perform(get(url).param("uid","1339296390"))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
    @Test
    public void loginGetTest() throws Exception {
        String url = "http://localhost:8080/loginModule/loginGet";
        MvcResult result = this.mockMvc.perform(get(url)
                .param("uid","1339296390")
                .param("password","938013"))
            .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
    @Test
    public void registerGetTest() throws Exception{
        String codeurl = "http://localhost:8080/loginModule/codeGet";
        String url = "http://localhost:8080/loginModule/registerGet";
        MvcResult coderesult = this.mockMvc.perform(get(codeurl)
        .param("uid","1382113875"))
                .andReturn();
        String code = coderesult.getResponse().getContentAsString();
        MvcResult regresult = this.mockMvc.perform(get(url)
        .param("code",code)
        .param("uid","1382113875")
        .param("password","456321"))
                .andReturn();
    }
    @Test
    public void followsGetTest() throws Exception{
        String url = "http://localhost:8080/loginModule/followsGet";
        MvcResult result = this.mockMvc.perform(get(url)
                .param("uid","1339296390"))
                .andReturn();
        String s = "[1,2,3]";
        assert s.equals(result.getResponse().getContentAsString());
    }
    @AfterEach
    public void after(){
        logger.info("测试结束");
    }
}
