package com.prince.myproj.brain.controllers;

import com.prince.myproj.brain.services.HandleQuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/brain")
public class HandleQuestionController {
    public static final Logger logger = Logger.getLogger(HandleQuestionController.class);

    @Autowired
    private HandleQuestionService handleQuestionService;


    @RequestMapping("/handle/txt")
    @ResponseBody
    public Object handleText(HttpServletRequest request, HttpServletResponse response, Model model){
        // http://localhost:9999/brain/handle/txt
        handleQuestionService.doHandleText();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","handle success");
        return map;
    }


}
