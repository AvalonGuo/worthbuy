package com.shop.worth2buy.domain;

import lombok.Data;

@Data
public class Report {
    private Integer id;
    private Integer GoodID;
    private String gdname;
    private String uid;
    private String reason;
    public Report(Integer GoodID,String gdname,String uid,String reason){
        this.GoodID=GoodID;
        this.gdname = gdname;
        this.uid=uid;
        this.reason=reason;
    }
    public Report(){

    }
}
