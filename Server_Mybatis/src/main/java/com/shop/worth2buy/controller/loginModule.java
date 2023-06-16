package com.shop.worth2buy.controller;

import com.shop.worth2buy.Utils.CodeUtils;
import com.shop.worth2buy.domain.User;
import com.shop.worth2buy.mapper.UserMapper;
import com.shop.worth2buy.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "loginModule")
public class loginModule {
    @Autowired
    LoginService loginService;
    @GetMapping(value = "loginGet")
    public boolean loginGet(HttpServletRequest req){
        return loginService.login(req);
    }

    @GetMapping(value = "codeGet")
    public String codeGet(HttpServletRequest req){
        return loginService.createCode(req);
    }

    @GetMapping(value = "fgPassword")
    public boolean fgPassword(HttpServletRequest req){
        return loginService.fgPassword(req);
    }

    @GetMapping(value = "registerGet")
    public boolean registerGet(HttpServletRequest request){
        return loginService.register(request);
    }

}
