package com.prince.myproj.brain.controllers;

import com.prince.myproj.brain.dao.BrainWordModelMapper;
import com.prince.myproj.brain.models.BrainWordModel;
import com.prince.myproj.brain.services.BrainWordService;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/brain")
public class WordsController {

    public static final Logger logger = Logger.getLogger(WordsController.class);

    @Autowired
    private BrainWordService brainWordService;

    @RequestMapping("/rw")
    @ResponseBody
    public Object randomWords(HttpServletRequest request, HttpServletResponse response){
        // http://localhost:9999/brain/rw
        int day = StringUtil.parseIntFromRequest(request,"day",0);
        AjaxBean ajaxBean = brainWordService.getWordsByDay(day);
        return ajaxBean;
    }


}
