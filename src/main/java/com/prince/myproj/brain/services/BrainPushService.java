package com.prince.myproj.brain.services;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.brain.dao.BrainPushModelMapper;
import com.prince.myproj.brain.dao.BrainUserDao;
import com.prince.myproj.brain.models.BrainPushModel;
import com.prince.myproj.brain.models.BrainUserModel;
import com.prince.myproj.brain.models.BrainWordRecordModel;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.DateUtil;
import com.prince.myproj.util.HttpUtil;
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

import java.util.Date;
import java.util.List;

@Service
public class BrainPushService {
    public static final Logger logger = Logger.getLogger(BrainPushService.class);

    @Autowired
    private TokenService tokenService;

    private Configuration config;
    {
        try {
            config = new PropertiesConfiguration("weixin.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private BrainPushModelMapper brainPushModelMapper;

    @Autowired
    private BrainUserDao brainUserDao;

    @Autowired
    private BrainRecordService brainRecordService;


    public AjaxBean collectFormId(String openId,String formId){
        AjaxBean ajaxBean = new AjaxBean();

        BrainPushModel brainPushModel = new BrainPushModel();
        brainPushModel.setFormId(formId);
        brainPushModel.setOpenId(openId);
        brainPushModel.setAlive(1);
        brainPushModel.setCreateTime(new Date());
        brainPushModelMapper.insert(brainPushModel);

        ajaxBean.setError(ErrorCode.SUCCESS);
        ajaxBean.setData(brainPushModel);
        return ajaxBean;
    }

    public AjaxBean autoPushDaily(){
        AjaxBean ajaxBean = new AjaxBean();

        List<BrainUserModel> brainUserModels = brainUserDao.selectAllUser(null);
        for(int i=0;i<brainUserModels.size();i++){
            BrainUserModel brainUserModel = brainUserModels.get(i);
            pushDailyRemind(brainUserModel);
        }

        ajaxBean.setData(ErrorCode.SUCCESS);
        return ajaxBean;
    }

    public void pushDailyRemind(String openId){
        BrainUserModel brainUserModel = new BrainUserModel();
        brainUserModel.setOpenId(openId);
        brainUserModel = brainUserDao.selectByOpenId(brainUserModel);
        pushDailyRemind(brainUserModel);
    }

    public void pushDailyRemind(BrainUserModel brainUserModel){
        String openId = brainUserModel.getOpenId();
        List<BrainPushModel> brainPushModels = findListByOpenId(openId);
        BrainPushModel selectBrainPushModel = findOneAlivePushModel(brainPushModels);
        if(selectBrainPushModel!=null){
            sendDailyRemind(selectBrainPushModel,brainUserModel);
            selectBrainPushModel.setAlive(0);
            brainPushModelMapper.updateByPrimaryKeySelective(selectBrainPushModel);
        }
    }

    private void sendDailyRemind(BrainPushModel brainPushModel,BrainUserModel brainUserModel){
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
        templateMsgItemBean1.setValue("每日单词任务");
        templateMsgItemBean1.setColor("#173177");
        templateMsgDataBean.setKeyword1(templateMsgItemBean1);

        int dayNum = searchMarkDayNum(brainUserModel);
        TemplateMsgItemBean templateMsgItemBean2 = new TemplateMsgItemBean();
        templateMsgItemBean2.setValue(""+dayNum);
        templateMsgItemBean2.setColor("#173177");
        templateMsgDataBean.setKeyword2(templateMsgItemBean2);


        TemplateMsgItemBean templateMsgItemBean3 = new TemplateMsgItemBean();
        templateMsgItemBean3.setValue(brainUserModel.getNickName()+",不积跬步，如何至千里，不要偷懒哦！");
        templateMsgItemBean3.setColor("#173177");
        templateMsgDataBean.setKeyword3(templateMsgItemBean3);

        TemplateMsgItemBean templateMsgItemBean4 = new TemplateMsgItemBean();
        templateMsgItemBean4.setValue(""+searchAllMarkUser());
        templateMsgItemBean4.setColor("#173177");
        templateMsgDataBean.setKeyword4(templateMsgItemBean4);

        pushMsg(JSONObject.parseObject(JSONObject.toJSONString(dailyMsgModel)));

    }

    private int searchMarkDayNum(BrainUserModel brainUserModel){
        BrainWordRecordModel brainWordRecordModel = brainRecordService.loadRecordFromDB(brainUserModel.getOpenId());
        return brainWordRecordModel.getDayIndex()+1;
    }

    private int searchAllMarkUser(){
        return brainRecordService.getAllRecordNum();
    }

    private void pushMsg(JSONObject jsonObject){
        String url = config.getString("template_msg_url")+"?access_token="+tokenService.getToken();
        logger.info(jsonObject);
        String result = HttpUtil.postJSON(url,jsonObject);
        logger.info(result);
    }

    private List<BrainPushModel> findListByOpenId(String openId){
        BrainPushModel brainPushModel = new BrainPushModel();
        brainPushModel.setAlive(1);
        brainPushModel.setOpenId(openId);
        List<BrainPushModel> brainPushModels = brainPushModelMapper.selectAlivePushId(brainPushModel);
        return brainPushModels;
    }

    private BrainPushModel findOneAlivePushModel(List<BrainPushModel> brainPushModels){
        if(brainPushModels!=null){
            Date now = new Date();
            for(int i=0;i<brainPushModels.size();i++){
                BrainPushModel brainPushModel = brainPushModels.get(i);

                Date createTime = brainPushModel.getCreateTime();
                int days = DateUtil.disFromDate(createTime,now);

                // 如果是在7天内
                if(days<7){
                    return brainPushModel;
                }else {
                    brainPushModel.setAlive(0);
                    brainPushModelMapper.updateByPrimaryKeySelective(brainPushModel);
                }
            }
        }
        return null;
    }

//    public AjaxBean pushMsgTest(){
//        AjaxBean ajaxBean = new AjaxBean();
//
//        String openId = "ou47z0CB-3Hjdl1MlRhrBAtaAC4c";
//        String formId = "fca9f0b9890b07389e7989894a2ed214";
//        String page =  "/pages/index/index?sss=sss";
//        String templateId = config.getString("daily_remind_id");
//
//        DailyMsgModel dailyMsgModel = new DailyMsgModel();
//        dailyMsgModel.setTouser(openId);
//        dailyMsgModel.setForm_id(formId);
//        dailyMsgModel.setPage(page);
//        dailyMsgModel.setTemplate_id(templateId);
//
//        String url = config.getString("template_msg_url")+"?access_token="+tokenService.getToken();
//        String jsonStr = JSONObject.toJSONString(dailyMsgModel);
//        HttpUtil.postJSON(url,JSONObject.parseObject(jsonStr));
//
//        ajaxBean.setError(ErrorCode.SUCCESS);
//        return ajaxBean;
//    }
}
