package com.shop.worth2buy.domain;

import lombok.Data;

@Data
public class MyGood {
    private Integer id;
    private String gdname;
    private Integer price;
    private String htry_price;
    private String first_pic;
    private String line;
    private String[] pic;
    private Integer deserve;
    private Integer not_deserve;
    public MyGood(Good good){
        this.id = good.getId();
        this.gdname = good.getGdname();
        this.price = good.getPrice();
        this.htry_price = good.getHtry_price();
        this.first_pic = good.getFirst_pic();
        this.line = good.getLine();
        this.pic = good.getPics_src().split(",");
        this.deserve = good.getDeserve();
        this.not_deserve = good.getNot_deserve();
    }
}
