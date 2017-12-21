package com.prince.myproj.wx.services;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.HttpUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    public static final Logger logger = Logger.getLogger(TokenService.class);
    private Configuration config;
    {
        try {
            config = new PropertiesConfiguration("weixin.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private String token;

    public AjaxBean getTokenAjaxBean(){
        if(token == null){
            reloadToken();
        }
        AjaxBean ajaxBean = new AjaxBean();
        if(token!=null){
            Map<String,String> map = new HashMap<String,String>();
            map.put("token",token);
            ajaxBean.setData(map);
            ajaxBean.setError(ErrorCode.SUCCESS);
        }else {
            ajaxBean.setError(ErrorCode.GET_TOKEN_ERROR);
        }
        return ajaxBean;
    }
    public String getToken(){
        if(this.token == null){
            reloadToken();
            if(this.token != null){
                new TokenThread(this).start();
            }
        }
        return this.token;
    }
    public void resetToken(){
        logger.info("resetToken:");
        this.token = null;
    }
    public void reloadToken(){
        String tokenUrl = config.getString("login_access_token");
        String appId = config.getString("appid");
        String appSecret = config.getString("AppSecret");
        String type = config.getString("grant_type");
        String url = tokenUrl+"?grant_type="+type+"&appid="+appId+"&secret="+appSecret;
        String tokenJsonStr = HttpUtil.getContentByUrl(url);
        logger.info(tokenJsonStr);
        JSONObject tokenJson = JSONObject.parseObject(tokenJsonStr);
        this.token = tokenJson.getString("access_token");
    }
}
class TokenThread extends Thread{
    private TokenService tokenService;
    public TokenThread(TokenService tokenService){
        this.tokenService = tokenService;
    }
    public void run() {
        try {
            Thread.sleep((long)(60*60*1000));
            this.tokenService.resetToken();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}