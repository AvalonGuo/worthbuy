package com.shop.worth2buy.mapper;

import com.shop.worth2buy.domain.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodMapper {
    @Select("select * from goods where id=#{id}")
    Good SelectByID(@Param("id") Integer id);
    @Select("select * from goods where gdname like CONCAT('%',#{match},'%')")
    List<Good> SelectByWord(@Param("match") String match);
    @Select("select * from goods where gdname=#{gdname}")
    Good SelectByGdname(@Param("gdname") String gdname);
    @Update("update goods set gdname=#{gdname},price=#{price},htry_price=#{htry_price},pics_src=#{pics_src},first_pic=#{first_pic},line=#{line}  where id=#{id}")
    void UpdateGood(Good good);
    @Update("update goods set deserve=#{deserve} where id=#{GoodID}")
    void UpdateDeserve(@Param("deserve") Integer deserve,Integer GoodID);
    @Update("update goods set not_deserve=#{not_deserve} where id=#{GoodID}")
    void UpdateNotDeserve(Integer not_deserve,Integer GoodID);
    @Insert("insert into goods(gdname,price,htry_price,pics_src,line) values(#{gdname},#{price},#{htry_price},#{pics_src},#{line})")
    void InsertNewGood(Good good);
    @Update("update goods set htry_price=#{htry_price} where gdname=#{gdname}")
    void UpdatePrice(String htry_price,String gdname);
}
