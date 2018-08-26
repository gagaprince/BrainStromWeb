package com.prince.myproj.brain.services;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.brain.dao.BrainPushModelMapper;
import com.prince.myproj.brain.dao.BrainUserDao;
import com.prince.myproj.brain.models.BrainPushModel;
import com.prince.myproj.brain.models.BrainUserModel;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.wx.bean.TemplateMsgBean;
import com.prince.myproj.wx.bean.TemplateMsgDataBean;
import com.prince.myproj.wx.bean.TemplateMsgItemBean;
import com.prince.myproj.wx.services.TokenService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuobiPushService {
    public static final Logger logger = Logger.getLogger(HuobiPushService.class);
    @Autowired
    private TokenService tokenService;

    @Autowired
    private BrainPushService brainPushService;

    @Autowired
    private BrainPushModelMapper brainPushModelMapper;


    private Configuration config;
    {
        try {
            config = new PropertiesConfiguration("weixin.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public AjaxBean pushHuoBiBuyMsg(String zt,String time,String desc,String openId){
        // 币种 发出时间 描述
        //获取push需要的bean
        List<BrainPushModel> brainPushModels = brainPushService.findListByOpenId(openId);
        BrainPushModel selectBrainPushModel = brainPushService.findOneAlivePushModel(brainPushModels);
        if(selectBrainPushModel!=null){
            sendHuoBiMsg(selectBrainPushModel,zt,time,desc);
            selectBrainPushModel.setAlive(0);
            brainPushModelMapper.updateByPrimaryKeySelective(selectBrainPushModel);
        }
        AjaxBean ajaxBean = new AjaxBean();
        ajaxBean.setData(ErrorCode.SUCCESS);
        return ajaxBean;
    }

    private void sendHuoBiMsg(BrainPushModel brainPushModel, String zt,String time,String desc){
        // 发送每日提醒消息

        String templateId = config.getString("daily_remind_id");
        String page =  "pages/index/index?from=push&templateId="+templateId;

        TemplateMsgBean dailyMsgModel = new TemplateMsgBean();
        dailyMsgModel.setTouser(brainPushModel.getOpenId());
        dailyMsgModel.setForm_id(brainPushModel.getFormId());
        dailyMsgModel.setPage(page);
        dailyMsgModel.setTemplate_id(templateId);

        TemplateMsgDataBean templateMsgDataBean = new TemplateMsgDataBean();
        dailyMsgModel.setData(templateMsgDataBean);

        TemplateMsgItemBean templateMsgItemBean1 = new TemplateMsgItemBean();
        templateMsgItemBean1.setValue(zt);
        templateMsgItemBean1.setColor("#173177");
        templateMsgDataBean.setKeyword1(templateMsgItemBean1);

        TemplateMsgItemBean templateMsgItemBean2 = new TemplateMsgItemBean();
        templateMsgItemBean2.setValue(time);
        templateMsgItemBean2.setColor("#173177");
        templateMsgDataBean.setKeyword2(templateMsgItemBean2);


        TemplateMsgItemBean templateMsgItemBean3 = new TemplateMsgItemBean();
        templateMsgItemBean3.setValue(desc);
        templateMsgItemBean3.setColor("#173177");
        templateMsgDataBean.setKeyword3(templateMsgItemBean3);

        brainPushService.pushMsg(JSONObject.parseObject(JSONObject.toJSONString(dailyMsgModel)));

    }
}
