package com.prince.myproj.brain.dao;

import com.prince.myproj.brain.models.BrainPushModel;

import java.util.List;

public interface BrainPushModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_push
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_push
     *
     * @mbggenerated
     */
    int insert(BrainPushModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_push
     *
     * @mbggenerated
     */
    int insertSelective(BrainPushModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_push
     *
     * @mbggenerated
     */
    BrainPushModel selectByPrimaryKey(Long id);

    List<BrainPushModel> selectAlivePushId(BrainPushModel brainPushModel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_push
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BrainPushModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_push
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BrainPushModel record);
}