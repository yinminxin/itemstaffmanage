package com.wonders.itemstaffmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    public void setSessionAttr(String s,Object o){
        request.getSession().setAttribute(s,o);
    }

    public Object getSessionAttr(String s){
        Object o=request.getSession().getAttribute(s);
        return o;
    }
}
