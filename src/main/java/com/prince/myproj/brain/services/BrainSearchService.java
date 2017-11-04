package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainMeanModelMapper;
import com.prince.myproj.brain.dao.BrainPosModelMapper;
import com.prince.myproj.brain.dao.BrainWordModelMapper;
import com.prince.myproj.brain.models.BrainMeanModel;
import com.prince.myproj.brain.models.BrainPosModel;
import com.prince.myproj.brain.models.BrainWordModel;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrainSearchService {
    public static final Logger logger = Logger.getLogger(BrainSearchService.class);

    @Autowired
    private BrainWordModelMapper brainWordModelMapper;

    @Autowired
    private BrainWordService brainWordService;

    public AjaxBean search(String word){
        logger.info("搜索词："+word);
        AjaxBean ajaxBean = new AjaxBean();
        try {
            BrainWordModel brainWordModel = brainWordModelMapper.selectByWord(word);
            if(brainWordModel == null){
                ajaxBean.setError(ErrorCode.WORD_NO_SEARCH_RESULT);
            }else{

                List<BrainWordModel> brainWordModels = new ArrayList<BrainWordModel>();
                brainWordModels.add(brainWordModel);

                brainWordService.finishAllMsgWithWordList(brainWordModels);

                ajaxBean.setError(ErrorCode.SUCCESS);
                ajaxBean.setData(brainWordModel);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxBean.setError(ErrorCode.WORD_SEARCH_ERROR);
        }
        return ajaxBean;
    }
}
