package com.prince.myproj.wx.services;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.brain.services.BrainUserService;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.DateUtil;
import com.prince.myproj.util.HttpUtil;
import com.prince.myproj.wx.bean.LoginBean;
import com.prince.myproj.brain.models.BrainUserModel;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

@Service
public class WXLoginService {
    public static final Logger logger = Logger.getLogger(WXLoginService.class);

    @Autowired
    private HttpSession session;
    @Autowired
    private DecodeService decodeService;
    @Autowired
    private BrainUserService brainUserService;

    private Configuration config;
    {
        try {
            config = new PropertiesConfiguration("weixin.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public AjaxBean login(String code,String encryptedData,String iv){
        AjaxBean ajaxBean = new AjaxBean();
        String loginResult = loginByCode(code);
        LoginBean loginBean = saveUserSession(loginResult);
        if(loginBean==null){
            ajaxBean.setError(ErrorCode.WX_CODE_ERROR);
            ajaxBean.setData(loginBean);
            return ajaxBean;
        }

        if(!brainUserService.userExist(loginBean.getOpenId())){
            //解密encry
            BrainUserModel brainUserModel = parseToUser(encryptedData,iv,loginBean);
            brainUserService.addUser(brainUserModel);
        }

        ajaxBean.setError(ErrorCode.SUCCESS);
        ajaxBean.setData(loginBean);
        return ajaxBean;
    }

    private BrainUserModel parseToUser(String encryptedData, String iv, LoginBean loginBean){
        String openId = loginBean.getOpenId();
        String sessionKey = (String)session.getAttribute(openId);
        String userInfo = decodeService.decodeByEncryIvSession(encryptedData,iv,sessionKey);
        logger.info(userInfo);
        JSONObject userInfoJson = JSONObject.parseObject(userInfo);
        BrainUserModel brainUserModel = new BrainUserModel();
        brainUserModel.setOpenId(userInfoJson.getString("openId"));
        brainUserModel.setCity(userInfoJson.getString("city"));
        brainUserModel.setAvatarUrl(userInfoJson.getString("avatarUrl"));
        brainUserModel.setCountry(userInfoJson.getString("country"));
        brainUserModel.setProvince(userInfoJson.getString("province"));
        brainUserModel.setNickName(userInfoJson.getString("nickName"));
        brainUserModel.setGender(userInfoJson.getInteger("gender"));
        brainUserModel.setUnionId(userInfoJson.getString("unionId"));
        brainUserModel.setCreateTime(DateUtil.now());
        brainUserModel.setUpdateTime(DateUtil.now());
        return brainUserModel;
    }

    private LoginBean saveUserSession(String loginResult){
        JSONObject loginObj = JSONObject.parseObject(loginResult);
        String errorCode = loginObj.getString("errcode");
        if(errorCode==null){
            String openId = loginObj.getString("openid");
            String sessionKey = loginObj.getString("session_key");
            session.setAttribute(openId,sessionKey);
            LoginBean loginBean = new LoginBean();
            loginBean.setOpenId(openId);
            return loginBean;
        }
        return null;
    }

    private String loginByCode (String code){
        String url = config.getString("login_for_wxapp");
        String appid = config.getString("appid");
        String secret = config.getString("AppSecret");
        String grant_type = "authorization_code";
        url = url+"?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type="+grant_type;
        logger.info(url);
        String result = HttpUtil.getContentByUrl(url);
        logger.info(result);
        return result;
    }
}
