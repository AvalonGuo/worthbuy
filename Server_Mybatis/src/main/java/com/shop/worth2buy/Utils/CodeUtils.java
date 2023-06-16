package com.shop.worth2buy.Utils;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtils {
    @CachePut(value = "VerifyCode",key = "#phone")
    public String createCode(String phone){
        String code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
        return code;
    }
    @Cacheable(value = "VerifyCode",key = "#phone")
    public String getCode(String phone){
        return null;
    }

}
