package com.shop.worth2buy.domain;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;



@Data
public class Good {
    private Integer id;
    private String gdname;
    private Integer price;
    private String htry_price;
    private String pics_src;
    private String first_pic;
    private String line;
    private Integer deserve = 0;
    private Integer not_deserve = 0;
    public Good(String gdname,Integer price,String htry_price){
        this.gdname = gdname;
        this.price = price;
        this.htry_price = htry_price;
    }
    public Good(String gdname,Integer price,String htry_price,String pics_src,String first_pic){
        this.gdname = gdname;
        this.htry_price=htry_price;
        this.price = price;
        this.pics_src = pics_src;
        this.first_pic = first_pic;
    }
    public Good(String gdname,Integer price,String htry_price,String pics_src,String first_pic,String line){
        this.gdname = gdname;
        this.htry_price=htry_price;
        this.price = price;
        this.pics_src = pics_src;
        this.first_pic = first_pic;
        this.line = line;
    }
    public Good(String gdname,Integer price,String htry_price,String  pics_src,String first_pic,String line,Integer deserve,Integer not_deserve){
        this.gdname=gdname;
        this.htry_price=htry_price;
        this.price = price;
        this.pics_src = pics_src;
        this.first_pic = first_pic;
        this.line = line;
        this.deserve = deserve;
        this.not_deserve = not_deserve;
    }
    public Good(){

    }
}
