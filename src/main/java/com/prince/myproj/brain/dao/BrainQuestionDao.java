package com.prince.myproj.brain.dao;

import com.prince.myproj.brain.models.BrainQuestionModel;

import java.util.List;
import java.util.Map;

public interface BrainQuestionDao {
    public void save(BrainQuestionModel b);
    public List<BrainQuestionModel> select(Map<String,Object> map);
}
