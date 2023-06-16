package com.shop.worth2buy.mapper;

import com.shop.worth2buy.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {
    @Insert("insert into comment(GoodID,uid,gdname,comment) values(#{GoodID},#{uid},#{gdname},#{comment})")
    void InsertNewComment(Comment comment);
    @Select("select * from comment where uid=#{uid}")
    List<Comment> SelectByUID(@Param("uid") String uid);
    @Select("select * from comment where GoodID=#{GoodID}")
    List<Comment> SelectByGoodID(@Param("GoodID") Integer GoodID);
}
