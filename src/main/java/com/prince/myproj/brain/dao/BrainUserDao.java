package com.prince.myproj.brain.dao;

import com.prince.myproj.brain.models.BrainUserModel;

import java.util.List;
import java.util.Map;

public interface BrainUserDao {
    public void save(BrainUserModel brainUserModel);
    public void updateByOpenId(BrainUserModel brainUserModel);
    public BrainUserModel selectByOpenId(BrainUserModel brainUserModel);
    public List<BrainUserModel> select(Map<String,Object> map);
    public List<BrainUserModel> selectAllUser(Map<String,Object> map);
}
