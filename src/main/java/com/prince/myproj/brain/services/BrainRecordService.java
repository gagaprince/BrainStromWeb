package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainWordRecordModelMapper;
import com.prince.myproj.brain.models.BrainWordModel;
import com.prince.myproj.brain.models.BrainWordRecordModel;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrainRecordService {
    public static final Logger logger = Logger.getLogger(BrainRecordService.class);

    @Autowired
    private BrainWordRecordModelMapper brainWordRecordModelMapper;

    @Autowired
    private BrainWordService brainWordService;

    private int historyDaySize = 5;
    private int daySize = 10;

    public AjaxBean loadRecord(String openId,String wordType){
        AjaxBean ajaxBean = new AjaxBean();

        // 拿到当前的dayindex
        BrainWordRecordModel brainWordRecordModel = loadRecordFromDB(openId);
        // 拿出历史需要记忆的数组
        List<BrainWordModel> historyModels = findHistoryWords(brainWordRecordModel.getDayIndex(),wordType);
        // 拿出今日需要记忆的新词
        List<BrainWordModel> newModels = findNewDayWords(brainWordRecordModel.getDayIndex(),wordType);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("historyWords",historyModels);
        resultMap.put("newWords",newModels);
        resultMap.put("historySize",historyModels.size());
        resultMap.put("newSize",newModels.size());
        resultMap.put("totalSize",historyModels.size()+newModels.size());
        resultMap.put("dayIndex",brainWordRecordModel.getDayIndex());

        ajaxBean.setError(ErrorCode.SUCCESS);
        ajaxBean.setData(resultMap);
        return ajaxBean;
    }

    private List<BrainWordModel> findNewDayWords(int dayIndex,String wordType){
        List<BrainWordModel> brainWordModels = brainWordService.findBrainWordModelsBySLAndWordType(dayIndex*daySize,daySize,wordType);
        brainWordService.finishAllMsgWithWordList(brainWordModels);
        return brainWordModels;
    }

    private List<BrainWordModel> findHistoryWords(int dayIndex,String wordType){
        int beginIndex = dayIndex-historyDaySize;
        int beginSize = 0;
        int length = 0;
        if(beginIndex>=0){
            beginSize = beginIndex*daySize;
            length = daySize*historyDaySize;
        }else {
            length = dayIndex*daySize;
        }
        List<BrainWordModel> brainWordModels = brainWordService.findBrainWordModelsBySLAndWordType(beginSize,length,wordType);
        brainWordService.finishAllMsgWithWordList(brainWordModels);
        return brainWordModels;
    }

    private BrainWordRecordModel loadRecordFromDB(String openId){
        BrainWordRecordModel brainWordRecordModel = brainWordRecordModelMapper.selectByOpenId(openId);
        if(brainWordRecordModel==null){
            // 说明用户是第一次进入 初始化内容
            brainWordRecordModel = new BrainWordRecordModel();
            brainWordRecordModel.setOpenId(openId);
            brainWordRecordModel.setDayIndex(0);
            brainWordRecordModel.setNowDay(DateUtil.today());
            brainWordRecordModelMapper.insertSelective(brainWordRecordModel);
        }else{
            Date recordDate = brainWordRecordModel.getNowDay();
            Date today = DateUtil.today();
            if(!DateUtil.equalsWithDate(recordDate,today)){
                // 如果已经过了一天了 则将记录+1
                brainWordRecordModel.setDayIndex(brainWordRecordModel.getDayIndex()+1);
                brainWordRecordModel.setNowDay(today);
                brainWordRecordModelMapper.updateByPrimaryKeySelective(brainWordRecordModel);
            }
        }
        return brainWordRecordModel;
    }

    public AjaxBean loadRecordNum(String openId){
        AjaxBean ajaxBean = new AjaxBean();

        // 拿到当前的dayindex
        BrainWordRecordModel brainWordRecordModel = loadRecordFromDB(openId);

        int dayIndex = brainWordRecordModel.getDayIndex();
        int historyBeginIndex = dayIndex-historyDaySize;
        int historySize = 0;
        if(historyBeginIndex>=0){
            historySize = historyDaySize*daySize;
        }else{
            historySize = dayIndex*daySize;
        }

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("historySize",historySize);
        resultMap.put("newSize",daySize);
        resultMap.put("totalSize",historySize+daySize);
        resultMap.put("dayIndex",dayIndex);

        ajaxBean.setError(ErrorCode.SUCCESS);
        ajaxBean.setData(resultMap);
        return ajaxBean;
    }

}
