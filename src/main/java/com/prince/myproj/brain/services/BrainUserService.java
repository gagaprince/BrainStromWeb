package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainUserDao;
import com.prince.myproj.brain.models.BrainUserModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrainUserService {
    public static final Logger logger = Logger.getLogger(BrainUserService.class);

    @Autowired
    private BrainUserDao brainUserDao;

    public boolean userExist(String openId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("openId",openId);
        List<BrainUserModel> userList = brainUserDao.select(map);
        return userList.size()==1;
    }

    public void addUser(BrainUserModel brainUserModel){
        brainUserDao.save(brainUserModel);
    }

}
