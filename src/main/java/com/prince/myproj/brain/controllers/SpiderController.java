package com.prince.myproj.brain.controllers;

import com.prince.myproj.brain.services.SpiderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/brain")
public class SpiderController {
    public static final Logger logger = Logger.getLogger(SpiderController.class);

    @Autowired
    private SpiderService spiderService;

    @RequestMapping("/spider/sentence")
    @ResponseBody
    public Object spiderSentence(){
        // http://localhost:9999/brain/spider/sentence
        return spiderService.spiderSentence();
    }

    @RequestMapping("/spider/energy")
    @ResponseBody
    public Object spiderEnergy(){
        // http://localhost:9999/brain/spider/energy
        return spiderService.spiderEnergy();
    }
}
