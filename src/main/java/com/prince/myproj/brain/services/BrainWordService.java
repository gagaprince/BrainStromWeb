package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainMeanModelMapper;
import com.prince.myproj.brain.dao.BrainPosModelMapper;
import com.prince.myproj.brain.dao.BrainWordModelMapper;
import com.prince.myproj.brain.models.BrainMeanModel;
import com.prince.myproj.brain.models.BrainPosModel;
import com.prince.myproj.brain.models.BrainWordModel;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrainWordService {

    public static final Logger logger = Logger.getLogger(BrainUserService.class);

    @Autowired
    private BrainWordModelMapper brainWordModelMapper;

    @Autowired
    private BrainMeanModelMapper brainMeanModelMapper;

    @Autowired
    private BrainPosModelMapper brainPosModelMapper;

    @Autowired
    private BrainSentenceService brainSentenceService;

    public AjaxBean getWordsByDay(int day){
        AjaxBean ajaxBean = new AjaxBean();
        // 根据不同天搜索不同的单词
        // 1.从word表中 按照词频排序 从高到底 找出 第day*10 到 day*10+10的单词
        // 2.从mean表中 查找找出的单词的中文意思
        // 3.从pos表中 找出单词的词性

        List<BrainWordModel> brainWordModels = findBrainWordModelsBySLAndWordType(10*day,10,null);
        finishAllMsgWithWordList(brainWordModels);

        ajaxBean.setData(brainWordModels);
        ajaxBean.setError(ErrorCode.SUCCESS);
        return ajaxBean;
    }

    public void finishAllMsgWithWordList(List<BrainWordModel> brainWordModels){
        if(brainWordModels.size()>0) {
            Map<Integer, BrainWordModel> brainWordModelMap = parseWordListToMap(brainWordModels);
            findMeansWithList(brainWordModels, brainWordModelMap);
            findPosWithList(brainWordModels);
            brainSentenceService.findSentencesWithWords(brainWordModels);
        }
    }

    private void findPosWithList(List<BrainWordModel> brainWordModels){
        // 根据brainwordmodels 查找means
        List<BrainPosModel> brainPosModels = brainPosModelMapper.selectByWordList(brainWordModels);
        Map<Integer,BrainPosModel> brainPosModelMap = parsePosListToMap(brainPosModels);

        for(int i=0;i<brainWordModels.size();i++){
            BrainWordModel brainWordModel = brainWordModels.get(i);
            List<BrainMeanModel> brainMeanModels = brainWordModel.getBrainMeanModels();
            for(int j=0;j<brainMeanModels.size();j++){
                BrainMeanModel brainMeanModel = brainMeanModels.get(j);
                BrainPosModel brainPosModel = brainPosModelMap.get(brainMeanModel.getPosid());
                brainMeanModel.setBrainPosModel(brainPosModel);
            }
        }
    }

    private Map<Integer,BrainPosModel> parsePosListToMap(List<BrainPosModel> brainPosModels){
        Map<Integer,BrainPosModel> brainPosModelMap = new HashMap<Integer, BrainPosModel>();
        for(int i=0;i<brainPosModels.size();i++){
            BrainPosModel brainPosModel = brainPosModels.get(i);
            brainPosModelMap.put(brainPosModel.getId(),brainPosModel);
        }
        return brainPosModelMap;
    }

    private void findMeansWithList(List<BrainWordModel> brainWordModels,Map<Integer,BrainWordModel> brainWordModelMap){
        // 根据brainwordmodels 查找means
        List<BrainMeanModel> brainMeanModels = brainMeanModelMapper.selectByWordList(brainWordModels);
        for(int i=0;i<brainMeanModels.size();i++){
            BrainMeanModel brainMeanModel = brainMeanModels.get(i);
            parseMeansToUTF8(brainMeanModel);
            BrainWordModel brainWordModel = brainWordModelMap.get(brainMeanModel.getWordid());
            brainWordModel.addBrainMeanModel(brainMeanModel);
        }
    }

    private void parseMeansToUTF8(BrainMeanModel brainMeanModel){
        String means = brainMeanModel.getMeans();
        means = means.replaceAll("\"","").replaceAll("u","%u");
//        logger.info(means);
        means = StringUtil.convertUnicodeToUTF(means);
        brainMeanModel.setMeans(means);
    }

    private Map<Integer,BrainWordModel> parseWordListToMap(List<BrainWordModel> brainWordModels){
        Map<Integer,BrainWordModel> brainWordModelMap = new HashMap<Integer, BrainWordModel>();
        for(int i=0;i<brainWordModels.size();i++){
            BrainWordModel brainWordModel = brainWordModels.get(i);
            brainWordModelMap.put(brainWordModel.getId(),brainWordModel);
        }
        return brainWordModelMap;
    }

    public List<BrainWordModel> findBrainWordModelsBySLAndWordType(long start, long length,String wordType){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("start",start);
        map.put("length",length);
        map.put("wordType",wordType);
        return brainWordModelMapper.selectByStartLengthAndType(map);
    }

}
