package com.shop.worth2buy.controller;

import com.shop.worth2buy.domain.Comment;
import com.shop.worth2buy.domain.Report;
import com.shop.worth2buy.service.MyInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("MyInfoModule")
public class MyInfoModule {
    @Autowired
    MyInfoService myInfoService;
    @GetMapping("Report")
    public boolean Report(HttpServletRequest request){
        return myInfoService.InsertReport(request);
    }
    @GetMapping("GetReportByUid")
    public List<Report> GetReportByUid(HttpServletRequest request){
        return myInfoService.SelectReport(request);
    }
    @GetMapping("GetCommentByGID")
    public List<Comment> GetCommentByGID(HttpServletRequest request){
        return myInfoService.SelectCommentByGID(request);
    }
    @GetMapping("InsertComment")
    public void InsertComment(HttpServletRequest request){
        myInfoService.InsertComment(request);
    }
    @GetMapping("GetCommentByUID")
    public List<Comment> GetCommentByUID(HttpServletRequest request){
        return myInfoService.SelectCommentByUID(request);
    }
}
