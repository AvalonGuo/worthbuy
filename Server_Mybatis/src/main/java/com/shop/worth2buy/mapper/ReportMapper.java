package com.shop.worth2buy.mapper;

import com.shop.worth2buy.domain.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMapper {
    @Select("select * from report where uid=#{uid}")
    List<Report> SelectByUID(@Param("uid") String uid);
    @Insert("insert into report(GoodID,gdname,uid,reason) values(#{GoodID},#{gdname},#{uid},#{reason})")
    void InsertReport(Report report);
}
