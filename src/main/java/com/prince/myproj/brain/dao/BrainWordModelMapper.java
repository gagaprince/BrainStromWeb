package com.prince.myproj.brain.dao;

import com.prince.myproj.brain.models.BrainWordModel;

public interface BrainWordModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_words
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_words
     *
     * @mbggenerated
     */
    int insert(BrainWordModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_words
     *
     * @mbggenerated
     */
    int insertSelective(BrainWordModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_words
     *
     * @mbggenerated
     */
    BrainWordModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_words
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BrainWordModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_words
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BrainWordModel record);
}