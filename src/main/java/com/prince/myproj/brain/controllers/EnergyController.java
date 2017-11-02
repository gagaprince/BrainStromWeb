package com.prince.myproj.brain.controllers;

import com.prince.myproj.brain.services.EnergyService;
import com.prince.myproj.common.bean.AjaxBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("brain")
public class EnergyController {
    public static final Logger logger = Logger.getLogger(EnergyController.class);

    @Autowired
    private EnergyService energyService;

    @RequestMapping("/energy")
    @ResponseBody
    public AjaxBean todayEnergy(HttpServletRequest request, HttpServletResponse response){
        // http://127.0.0.1:9999/brain/energy
        return energyService.giveMeTodayEnergy();
    }

}
