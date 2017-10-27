package com.prince.myproj.wx.controllers;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.util.RequestUtil;
import com.prince.myproj.util.StringUtil;
import com.prince.myproj.wx.services.WXLoginService;
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
public class LoginController {
    public static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private WXLoginService wxLoginService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response){
        Object ro;
        try {
            JSONObject bodyJson = RequestUtil.getRequestBody(request);
            logger.info(bodyJson);

            String code = StringUtil.getString(bodyJson.getString("code"),"");
            String encryptedData = StringUtil.getString(bodyJson.getString("encryptedData"),"");
            String iv = StringUtil.getString(bodyJson.getString("iv"),"");

            ro = wxLoginService.login(code,encryptedData,iv);

        } catch (IOException e) {
            e.printStackTrace();
            ro = new Object();
        }

        return ro;
    }
}
