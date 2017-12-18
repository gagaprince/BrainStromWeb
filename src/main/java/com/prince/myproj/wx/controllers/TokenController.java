package com.prince.myproj.wx.controllers;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.RequestUtil;
import com.prince.myproj.util.StringUtil;
import com.prince.myproj.wx.services.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/brain/wx")
public class TokenController {
    public static final Logger logger = Logger.getLogger(TokenController.class);

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/getToken")
    @ResponseBody
    public Object getToken(HttpServletRequest request, HttpServletResponse response){
        return tokenService.getTokenAjaxBean();
    }

    @RequestMapping("/reloadToken")
    @ResponseBody
    public Object reloadToken(HttpServletRequest request, HttpServletResponse response){
        tokenService.reloadToken();
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setError(ErrorCode.SUCCESS);
        return ajaxBean;
    }
}
