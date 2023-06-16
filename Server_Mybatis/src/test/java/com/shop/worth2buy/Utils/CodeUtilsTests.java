package com.shop.worth2buy.Utils;

import com.shop.worth2buy.Utils.CodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeUtilsTests {

    @Autowired
    private CodeUtils codeUtils;
    @Test
    void getCode(){
        String phone = "1339296390";
        String verifycode;
        verifycode=codeUtils.createCode(phone);
        System.out.println(verifycode);
    }
    @Test
    void checkCode(){
        String toverifycode = codeUtils.getCode("1339296390");
        System.out.println(toverifycode);
    }

}
