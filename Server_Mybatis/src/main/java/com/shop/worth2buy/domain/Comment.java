package com.shop.worth2buy.domain;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer GoodID;
    private String uid;
    private String gdname;
    private String comment;
    public Comment(Integer GoodID,String uid,String gdname,String comment){
        this.gdname = gdname;
        this.GoodID=GoodID;
        this.uid = uid;
        this.comment = comment;
    }
    public Comment(){

    }
}
