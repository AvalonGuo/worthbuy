package com.shop.worth2buy.service;

import com.shop.worth2buy.domain.Comment;
import com.shop.worth2buy.domain.Report;
import com.shop.worth2buy.domain.User;
import com.shop.worth2buy.mapper.CommentMapper;
import com.shop.worth2buy.mapper.ReportMapper;
import com.shop.worth2buy.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MyInfoServiceImpl implements MyInfoService {
    private Logger MyInfoServiceLogger = Logger.getLogger("MyInfoService");
    @Autowired
    ReportMapper reportMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean InsertReport(HttpServletRequest request){
        Integer GoodID = Integer.parseInt(request.getParameter("GoodID"));
        String uid = request.getParameter("uid");
        String reason = request.getParameter("reason");
        String gdname = request.getParameter("gdname");
        Report report = new Report(GoodID,gdname,uid,reason);
        reportMapper.InsertReport(report);
        return true;
    }
    @Override
    public List<Report> SelectReport(HttpServletRequest request){
        String uid = request.getParameter("uid");
        List<Report> reportList = reportMapper.SelectByUID(uid);
        return reportList;
    }
    @Override
    public List<Comment> SelectCommentByGID(HttpServletRequest request){
        Integer goodID = Integer.parseInt(request.getParameter("GoodID"));
        return commentMapper.SelectByGoodID(goodID);
    }
    @Override
    public void InsertComment(HttpServletRequest request){
        Integer GoodID = Integer.parseInt(request.getParameter("GoodID"));
        String uid = request.getParameter("uid");
        String gdname = request.getParameter("gdname");
        String com = request.getParameter("input");
        Comment comment = new Comment(GoodID,uid,gdname,com);
        commentMapper.InsertNewComment(comment);

    }
    @Override
    public List<Comment> SelectCommentByUID(HttpServletRequest request){
        String uid = request.getParameter("uid");
        User user = userMapper.SelectByUid(uid);
        String follow = user.getFollows();
        String[] strings = follow.split(",");
        List<Comment> commentList = new ArrayList<>();
        for(int i=0;i<strings.length;i++){
            List<Comment> commentList1 =commentMapper.SelectByGoodID(Integer.parseInt(strings[i]));
            for(int j=0;j<commentList1.size();j++){
                if(!commentList1.get(j).getUid().equals(uid)){
                    commentList.add(commentList1.get(j));
                }
            }
        }
        return  commentList;
    }
}
