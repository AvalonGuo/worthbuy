package com.shop.worth2buy.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface LoginService {
    public abstract boolean login(HttpServletRequest request);
    public abstract String createCode(HttpServletRequest request);
    public abstract boolean fgPassword(HttpServletRequest request);
    public abstract boolean register(HttpServletRequest request);
}
