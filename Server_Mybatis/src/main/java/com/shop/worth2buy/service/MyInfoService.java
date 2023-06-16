package com.shop.worth2buy.service;

import com.shop.worth2buy.domain.Comment;
import com.shop.worth2buy.domain.Report;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface MyInfoService {
    public abstract boolean InsertReport(HttpServletRequest request);
    public abstract List<Report> SelectReport(HttpServletRequest request);
    public abstract List<Comment> SelectCommentByGID(HttpServletRequest request);
    public abstract void InsertComment(HttpServletRequest request);
    public abstract List<Comment> SelectCommentByUID(HttpServletRequest request);
}
