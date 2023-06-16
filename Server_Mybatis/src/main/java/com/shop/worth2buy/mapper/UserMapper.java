package com.shop.worth2buy.mapper;

import com.shop.worth2buy.domain.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    //查找
    @Select("select id,uid,password,follows,deserve_list,not_deserve_list from user where uid=#{uid}")
    User SelectByUid(@Param("uid") String uid); //@Param使变量名为uid,能在@Select中使用
    //插入
    @Insert("insert into user(uid,password) values(#{uid},#{password})")
    void InsertUser(User user);
    //更新
    @Update("update user set password=#{password},follows=#{follows} where uid=#{uid}")
    void UpdateUser(User usr);
    //管理员端--删除
    @Delete("delete from user where uid=#{uid}")
    void DeleteUser(User user);
    @Update("update user set deserve_list=#{deserve_list} where uid=#{uid}")
    void UpdateMyDeserve(User user);
    @Update("update user set not_deserve_list=#{not_deserve_list} where uid=#{uid}")
    void UpdateNotDeserve(User user);
}
