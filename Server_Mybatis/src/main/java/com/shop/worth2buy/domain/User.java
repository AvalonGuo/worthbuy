package com.shop.worth2buy.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String uid;
    private String password;
    private String follows;
    private String deserve_list;
    private String not_deserve_list;
    public User(String uid,String password){
        this.uid = uid;
        this.password = password;
    }
    public User(String uid,String password,String follows){
        this.uid = uid;
        this.password = password;
        this.follows=follows;
    }
    public User(String uid,String password,String follows,String deserve_list){
        this.uid = uid;
        this.password = password;
        this.follows=follows;
        this.deserve_list = deserve_list;
    }
    public User(String uid,String password,String follows,String deserve_list,String notdeserve_list)
    {
        this.uid = uid;
        this.password = password;
        this.follows=follows;
        this.deserve_list = deserve_list;
        this.not_deserve_list=notdeserve_list;
    }
    public User(){}
}
