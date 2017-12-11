package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainWordCollectModelMapper;
import com.prince.myproj.brain.models.BrainUserModel;
import com.prince.myproj.brain.models.BrainWordCollectModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrainWordCollectService {
    public static final Logger logger = Logger.getLogger(BrainWordCollectService.class);

    @Autowired
    private BrainWordCollectModelMapper brainWordCollectModelMapper;

    public void addWord(Integer wordId, BrainUserModel brainUserModel){
        BrainWordCollectModel brainWordCollectModel = new BrainWordCollectModel();
        brainWordCollectModel.setOpenId(brainUserModel.getOpenId());
        brainWordCollectModel.setWordId(wordId);
        BrainWordCollectModel brainWordCollectModel1 = brainWordCollectModelMapper.selectByOpenIdAndWordId(brainWordCollectModel);
        if(brainWordCollectModel1!=null){
            brainWordCollectModel = brainWordCollectModel1;
        }
        brainWordCollectModel.setCreateTime(new Date());
        brainWordCollectModel.setIsAlive(1);
        if(brainWordCollectModel1!=null){
            brainWordCollectModelMapper.updateByPrimaryKey(brainWordCollectModel);
        }else {
            brainWordCollectModelMapper.insert(brainWordCollectModel);
        }

    }

    public BrainWordCollectModel searchWord(Integer wordId,BrainUserModel brainUserModel){
        BrainWordCollectModel brainWordCollectModel = new BrainWordCollectModel();
        brainWordCollectModel.setOpenId(brainUserModel.getOpenId());
        brainWordCollectModel.setWordId(wordId);
        brainWordCollectModel = brainWordCollectModelMapper.selectByOpenIdAndWordId(brainWordCollectModel);
        return brainWordCollectModel;
    }

    public void removeWord(Integer wordId,BrainUserModel brainUserModel){
        BrainWordCollectModel brainWordCollectModel = new BrainWordCollectModel();
        brainWordCollectModel.setOpenId(brainUserModel.getOpenId());
        brainWordCollectModel.setWordId(wordId);
        brainWordCollectModel = brainWordCollectModelMapper.selectByOpenIdAndWordId(brainWordCollectModel);
        brainWordCollectModel.setIsAlive(0);
        brainWordCollectModelMapper.updateByPrimaryKey(brainWordCollectModel);
    }

    public List<BrainWordCollectModel> searchByUser(BrainUserModel brainUserModel){
        return brainWordCollectModelMapper.selectByOpenId(brainUserModel.getOpenId());
    }
}
