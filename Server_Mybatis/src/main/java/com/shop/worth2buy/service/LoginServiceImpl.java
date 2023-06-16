package com.shop.worth2buy.service;

import com.shop.worth2buy.Utils.CodeUtils;
import com.shop.worth2buy.domain.User;
import com.shop.worth2buy.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class LoginServiceImpl implements LoginService {
    private Logger loginServiceLogger = Logger.getLogger("loginServiceImpl");
    @Autowired
    CodeUtils codeUtils;
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean login(HttpServletRequest request) {
        String password = request.getParameter("password");
        String uid = request.getParameter("uid");
        User login_user = userMapper.SelectByUid(uid);
        if (login_user.getPassword().equals(password)){
            loginServiceLogger.info("用户"+uid+"\t登录成功.");
            return true;
        }else {
            loginServiceLogger.info("用户"+uid+"\t登录失败");
            return false;
        }
    }

    @Override
    public String createCode(HttpServletRequest request) {
        String phone = request.getParameter("uid");
        String code = codeUtils.createCode(phone);
        loginServiceLogger.info("生成验证码:"+code);
        return code;
    }

    @Override
    public boolean fgPassword(HttpServletRequest request) {
        String phone = request.getParameter("uid");
        String code = request.getParameter("code");
        String password = request.getParameter("password");
        if (codeUtils.getCode(phone).equals(code)){
            User user = new User();
            user.setPassword(password);
            user.setUid(phone);
            try {
                userMapper.UpdateUser(user);
                loginServiceLogger.info("用户:"+phone+"\t修改密码成功");
                return true;
            }catch (Exception e){
                loginServiceLogger.info("用户:"+phone+"\t修改密码失败");
                e.printStackTrace();
                return false;
            }
        }else{
            loginServiceLogger.info("验证码错误");
            return false;
        }
    }
    @Override
    public boolean register(HttpServletRequest request){
        String uid = request.getParameter("uid");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        if (codeUtils.getCode(uid)==null){
            loginServiceLogger.info("验证码错误");
            return false;
        }
        if (codeUtils.getCode(uid).equals(code)){
            User selector = userMapper.SelectByUid(uid);
            User user_new = new User(uid,password);
            if (selector==null){
                userMapper.InsertUser(user_new);
                loginServiceLogger.info("用户:"+uid+"\t注册成功");
                return true;
            }
            else {
                loginServiceLogger.info("用户名已存在");
                return false;
            }
        }else {
            loginServiceLogger.info("验证码错误");
            return false;
        }
    }

}
