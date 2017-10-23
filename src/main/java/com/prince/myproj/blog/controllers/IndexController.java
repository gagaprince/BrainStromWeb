package com.prince.myproj.blog.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gagaprince on 15-12-19.
 */
@Controller
@RequestMapping("/brain")
public class IndexController {

    public static final Logger logger = Logger.getLogger(IndexController.class);
    //首页mapping
    @RequestMapping("/index")
    public String viewIndex(HttpServletRequest request,HttpServletResponse response,Model model){
        return "index";
    }

    @RequestMapping("/index-api")
    @ResponseBody
    public Object indexApi(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("aaa","bbb");
        return map;
    }
}
