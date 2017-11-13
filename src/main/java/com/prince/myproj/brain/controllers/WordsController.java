package com.prince.myproj.brain.controllers;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.brain.dao.BrainWordModelMapper;
import com.prince.myproj.brain.models.BrainWordModel;
import com.prince.myproj.brain.services.BrainRecordService;
import com.prince.myproj.brain.services.BrainSearchService;
import com.prince.myproj.brain.services.BrainWordService;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.HttpUtil;
import com.prince.myproj.util.RequestUtil;
import com.prince.myproj.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/brain")
public class WordsController {

    public static final Logger logger = Logger.getLogger(WordsController.class);

    @Autowired
    private BrainWordService brainWordService;

    @Autowired
    private BrainRecordService brainRecordService;

    @Autowired
    private BrainSearchService brainSearchService;

    @RequestMapping("/rw")
    @ResponseBody
    public Object randomWords(HttpServletRequest request, HttpServletResponse response){
        // http://localhost:9999/brain/rw
        int day = StringUtil.parseIntFromRequest(request,"day",0);
        AjaxBean ajaxBean = brainWordService.getWordsByDay(day);
        return ajaxBean;
    }

    @RequestMapping("/loadRecord")
    @ResponseBody
    public Object loadRecord(HttpServletRequest request, HttpServletResponse response){
        // http://localhost:9999/brain/loadRecord
        try {
            JSONObject requestJson = RequestUtil.getRequestBody(request);
            String openId = requestJson.getString("openId");
            String wordType = requestJson.getString("wordType");
            return brainRecordService.loadRecord(openId,wordType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/loadRecordNum")
    @ResponseBody
    public Object loadRecordNum(HttpServletRequest request, HttpServletResponse response){
        // http://localhost:9999/brain/loadRecord
        try {
            JSONObject requestJson = RequestUtil.getRequestBody(request);
            String openId = requestJson.getString("openId");
            return brainRecordService.loadRecordNum(openId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/search")
    @ResponseBody
    public Object search(HttpServletRequest request, HttpServletResponse response){
        AjaxBean ajaxBean = null;
        try {
            JSONObject resJson = RequestUtil.getRequestBody(request);
            String word = resJson.getString("word");
            word = word.split("[\\.|\\!|,|\\?]")[0];
            ajaxBean = brainSearchService.search(word);
        } catch (IOException e) {
            e.printStackTrace();
            ajaxBean = new AjaxBean();
            ajaxBean.setError(ErrorCode.REQUEST_ERROR);
        }

        return ajaxBean;
    }

    @RequestMapping("/addCollect")
    @ResponseBody
    public Object collect(HttpServletRequest request, HttpServletResponse response){
        AjaxBean ajaxBean = null;
        try {
            JSONObject reqJson = RequestUtil.getRequestBody(request);
            int wordId = reqJson.getInteger("wordId");
            String openId = reqJson.getString("openId");
            ajaxBean = brainWordService.addWordCollect(wordId,openId);
        } catch (IOException e) {
            ajaxBean = new AjaxBean();
            ajaxBean.setError(ErrorCode.REQUEST_ERROR);
            e.printStackTrace();
        }
        return ajaxBean;
    }

    @RequestMapping("/removeCollect")
    @ResponseBody
    public Object reCollect(HttpServletRequest request, HttpServletResponse response){
        AjaxBean ajaxBean = null;
        try {
            JSONObject reqJson = RequestUtil.getRequestBody(request);
            int wordId = reqJson.getInteger("wordId");
            String openId = reqJson.getString("openId");
            ajaxBean = brainWordService.removeWordCollect(wordId,openId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ajaxBean;
    }

    @RequestMapping("/listCollect")
    @ResponseBody
    public Object listCollect(HttpServletRequest request, HttpServletResponse response){
        AjaxBean ajaxBean = null;
        try {
            JSONObject reqJson = RequestUtil.getRequestBody(request);
            String openId = reqJson.getString("openId");
            ajaxBean = brainWordService.listWordCollect(openId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ajaxBean;
    }


}
