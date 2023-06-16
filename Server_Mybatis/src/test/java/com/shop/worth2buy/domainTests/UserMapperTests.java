package com.shop.worth2buy.domainTests;

import com.shop.worth2buy.domain.Comment;
import com.shop.worth2buy.domain.User;
import com.shop.worth2buy.mapper.CommentMapper;
import com.shop.worth2buy.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author changqing
 */
@SpringBootTest
public class UserMapperTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentMapper commentMapper;
    @Test
    void SelectByUid(){
        User user = userMapper.SelectByUid("1339296390");
        System.out.println(user);
        System.out.println(user.getFollows());
    }
    @Test
    void CheckUidExits(){
        User user = userMapper.SelectByUid("1511310387");
        assert user==null:"user is not null";
    }
    @Test
    void DeleteUser(){
        User user = new User();
        user.setUid("158111387");
        user.setPassword("12346");
        userMapper.DeleteUser(user);
    }
    @Test
    void InsertUser(){
        User user = new User();
        user.setUid("158111387");
        user.setPassword("123456");
        userMapper.InsertUser(user);
    }
    @Test
    void UpdateUser(){
        String gdname = "偶来";
        String com = "不知道";
        String uid = "1581113875";
        Comment comment = new Comment(2,uid,gdname,com);
        comment.setComment(com);
        comment.setGdname(gdname);
        List<Comment> comment1 = commentMapper.SelectByGoodID(2);
        System.out.println(comment1);
        System.out.println(comment);
    }

}
