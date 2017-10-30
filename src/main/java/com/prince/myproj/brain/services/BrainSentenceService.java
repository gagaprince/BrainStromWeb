package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainSentenceModelMapper;
import com.prince.myproj.brain.models.BrainSentenceModel;
import com.prince.myproj.brain.models.BrainWordModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrainSentenceService {
    public static final Logger logger = Logger.getLogger(BrainSentenceService.class);

    @Autowired
    private BrainSentenceModelMapper brainSentenceModelMapper;

    public void findSentencesWithWords(List<BrainWordModel> brainWordModels){
        Map<String,BrainWordModel> brainWordModelMap = parseWordsToMap(brainWordModels);
        List<BrainSentenceModel> brainSentenceModels = brainSentenceModelMapper.selectSentencesWithWords(brainWordModels);
        for(int i=0;i<brainSentenceModels.size();i++){
            BrainSentenceModel brainSentenceModel = brainSentenceModels.get(i);
            BrainWordModel brainWordModel = brainWordModelMap.get(brainSentenceModel.getKeyWord());
            brainWordModel.addBrainSentenceModel(brainSentenceModel);
        }
    }

    private Map<String,BrainWordModel> parseWordsToMap(List<BrainWordModel> brainWordModels){
        Map<String,BrainWordModel> brainWordModelMap = new HashMap<String, BrainWordModel>();
        for(int i=0;i<brainWordModels.size();i++){
            BrainWordModel brainWordModel = brainWordModels.get(i);
            brainWordModelMap.put(brainWordModel.getWord(),brainWordModel);
        }
        return brainWordModelMap;
    }
}
