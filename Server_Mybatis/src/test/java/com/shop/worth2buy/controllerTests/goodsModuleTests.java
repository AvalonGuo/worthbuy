package com.shop.worth2buy.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class goodsModuleTests {
    private final Logger logger = Logger.getLogger("goodsModuleTests");
    MockMvc mockMvc;
    @BeforeEach
    void setUp(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void getHtryPriceTest() throws Exception {
        String url = "http://localhost:8080/goodsModule/htryPriceGet";
        MvcResult result = this.mockMvc.perform(get(url).param("Id","1"))
                .andReturn();
        String img_path = "../../images/lineChart1.png";
        assert (img_path.equals(result.getResponse().getContentAsString())):"the path is out of expectation";
    }
    @Test
    public void getFollowsTest() throws Exception{
        String url = "http://localhost:8080/goodsModule/FollowsGet";
        MvcResult result = this.mockMvc.perform(get(url).param("uid","1339296390"))
                .andReturn();

    }
    @Test
    public void getStarInfoTest() throws Exception{
        String url = "http://localhost:8080/goodsModule/getStarInfo";
        MvcResult result = this.mockMvc.perform(get(url)
                .param("uid","1339296390")
                .param("goodID","1")
                .param("star","1"))
                .andReturn();
        String res = result.getResponse().getContentAsString();
        assert res.equals("true"):"关注失败";
    }
    @Test
    public void getMatchGoodsTest() throws Exception{
        String url = "http://localhost:8080/goodsModule/getMatchGoods";
        MvcResult result = this.mockMvc.perform(get(url).
                param("match","手机")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assert (response.getContentAsString().equals("[]")):"返回不为列表";
    }
    @Test
    public void UpLoadGoodTest() throws Exception{
        String url = "http://localhost:8080/goodsModule/UpLoadGood";
        MvcResult result = this.mockMvc.perform(get(url)
                .param("gdname","手机")
                .param("price","25")
                .param("pic","../../images/ApplePhoneShell1.png,../../images/ApplePhoneShell2.png")
                .param("first_pic","../../images/ApplePhoneShell1.png")
        ).andReturn();
        assert result.getResponse().getContentAsString().equals("8"):"商品id不正确";
    }
}
