package com.shop.worth2buy.controllerTests;

import com.shop.worth2buy.domain.Comment;
import com.shop.worth2buy.domain.Report;
import com.shop.worth2buy.mapper.CommentMapper;
import com.shop.worth2buy.mapper.ReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class myInfoModule {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ReportMapper reportMapper;
    MockMvc mockMvc;
    @BeforeEach
    void setUp(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void GetComByUIDTest() throws Exception {
        String url = "http://localhost:8080/MyInfoModule/GetCommentByUID";
        MvcResult result = this.mockMvc.perform(get(url).param("uid","1339296390"))
                .andReturn();
        Integer length = result.getResponse().getContentLength();
        Integer flex = 1;
        assert length == flex :"长度相等";
    }
    public void InsertCommentTest() throws Exception {
        String url = "http://localhost:8080/MyInfoModule/InsertComment";
        MvcResult result = this.mockMvc.perform(get(url).param("uid","1339296390")
        .param("comment","实用很好")
        .param("gdname","水杯")
        .param("GoodID","1"))
                .andReturn();
        List<Comment> commentList = commentMapper.SelectByGoodID(1);
        assert commentList.size()==1:"插入失败";
    }
    public void InsetReportTest() throws Exception{
        String url = "http://localhost:8080/MyInfoModule/Report";
        MvcResult result = this.mockMvc.perform(get(url).param("uid","1339296390")
                .param("reason","[恶意欺骗,虚假信息,言语辱骂]")
                .param("gdname","水杯")
                .param("GoodID","1"))
                .andReturn();
        List<Report> reportList =reportMapper.SelectByUID("1339296390");
        assert reportList.get(0).getGoodID() == 1:"插入成功";
    }
}
