package com.prince.myproj.brain.controllers;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.brain.services.BrainPushService;
import com.prince.myproj.brain.services.EnergyService;
import com.prince.myproj.brain.services.HuobiPushService;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.util.RequestUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("brain")
public class PushController {
    public static final Logger logger = Logger.getLogger(PushController.class);

    @Autowired
    BrainPushService brainPushService;

    @Autowired
    HuobiPushService huobiPushService;

    @RequestMapping("/collectFormId")
    @ResponseBody
    public AjaxBean collectFormId(HttpServletRequest request, HttpServletResponse response){
        // http://127.0.0.1:9999/brain/collectFormId
        AjaxBean ajaxBean = null;
        JSONObject reqJson = null;
        try {
            reqJson = RequestUtil.getRequestBody(request);
            String openId = reqJson.getString("openId");
            String formId = reqJson.getString("formId");
            ajaxBean = brainPushService.collectFormId(openId,formId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ajaxBean;
    }

    @RequestMapping("/autoPushDaily")
    @ResponseBody
    public AjaxBean autoPushDaily(){
        AjaxBean ajaxBean = brainPushService.autoPushDaily();
        return ajaxBean;
    }

    @RequestMapping("/pushHuoBiMsg")
    @ResponseBody
    public AjaxBean pushHuoBiMsg(HttpServletRequest request){
        String openId = request.getParameter("openId");
        String zt = request.getParameter("zt");
        logger.info(zt);
        String desc = request.getParameter("desc");
        String time = request.getParameter("time");
        AjaxBean ajaxBean = huobiPushService.pushHuoBiBuyMsg(zt,time,desc,openId);
        return ajaxBean;
    }

    @RequestMapping("/pushTest")
    @ResponseBody
    public AjaxBean pushTest(HttpServletRequest request, HttpServletResponse response){
        // http://127.0.0.1:9999/brain/pushTest
        AjaxBean ajaxBean = null;
        JSONObject reqJson = null;
//        ajaxBean = brainPushService.pushMsgTest();
        brainPushService.pushDailyRemind("ou47z0CB-3Hjdl1MlRhrBAtaAC4c");
        return ajaxBean;
    }

}
