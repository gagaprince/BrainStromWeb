package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainEnergyModelMapper;
import com.prince.myproj.brain.models.BrainEnergyModel;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnergyService {
    public static final Logger logger = Logger.getLogger(EnergyService.class);

    @Autowired
    private BrainEnergyModelMapper brainEnergyModelMapper;

    public AjaxBean giveMeTodayEnergy(){
        AjaxBean ajaxBean = new AjaxBean();
        int index = parseTodayToNum();
        try {
            List<BrainEnergyModel> brainEnergyModelList = brainEnergyModelMapper.selectAll();
            int size = brainEnergyModelList.size();
            index = index%size;

            ajaxBean.setData(brainEnergyModelList.get(index));
            ajaxBean.setError(ErrorCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            ajaxBean.setError(ErrorCode.ENERGY_SELECT_ERROR);
        }
        return ajaxBean;
    }

    private int parseTodayToNum(){
        // 将今日与2017-11-01做差 取对应energy
        return DateUtil.disFromDate("2017-11-01",new Date());
    }
}
