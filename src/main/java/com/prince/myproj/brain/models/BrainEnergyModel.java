package com.prince.myproj.brain.models;

import java.io.Serializable;
import java.util.Date;

public class BrainEnergyModel implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brain_energy.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brain_energy.english
     *
     * @mbggenerated
     */
    private String english;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brain_energy.chinese
     *
     * @mbggenerated
     */
    private String chinese;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brain_energy.voice
     *
     * @mbggenerated
     */
    private String voice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brain_energy.source
     *
     * @mbggenerated
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brain_energy.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table brain_energy
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brain_energy.id
     *
     * @return the value of brain_energy.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brain_energy.id
     *
     * @param id the value for brain_energy.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brain_energy.english
     *
     * @return the value of brain_energy.english
     *
     * @mbggenerated
     */
    public String getEnglish() {
        return english;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brain_energy.english
     *
     * @param english the value for brain_energy.english
     *
     * @mbggenerated
     */
    public void setEnglish(String english) {
        this.english = english == null ? null : english.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brain_energy.chinese
     *
     * @return the value of brain_energy.chinese
     *
     * @mbggenerated
     */
    public String getChinese() {
        return chinese;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brain_energy.chinese
     *
     * @param chinese the value for brain_energy.chinese
     *
     * @mbggenerated
     */
    public void setChinese(String chinese) {
        this.chinese = chinese == null ? null : chinese.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brain_energy.voice
     *
     * @return the value of brain_energy.voice
     *
     * @mbggenerated
     */
    public String getVoice() {
        return voice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brain_energy.voice
     *
     * @param voice the value for brain_energy.voice
     *
     * @mbggenerated
     */
    public void setVoice(String voice) {
        this.voice = voice == null ? null : voice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brain_energy.source
     *
     * @return the value of brain_energy.source
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brain_energy.source
     *
     * @param source the value for brain_energy.source
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brain_energy.create_time
     *
     * @return the value of brain_energy.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brain_energy.create_time
     *
     * @param createTime the value for brain_energy.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_energy
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BrainEnergyModel other = (BrainEnergyModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEnglish() == null ? other.getEnglish() == null : this.getEnglish().equals(other.getEnglish()))
            && (this.getChinese() == null ? other.getChinese() == null : this.getChinese().equals(other.getChinese()))
            && (this.getVoice() == null ? other.getVoice() == null : this.getVoice().equals(other.getVoice()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_energy
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEnglish() == null) ? 0 : getEnglish().hashCode());
        result = prime * result + ((getChinese() == null) ? 0 : getChinese().hashCode());
        result = prime * result + ((getVoice() == null) ? 0 : getVoice().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table brain_energy
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", english=").append(english);
        sb.append(", chinese=").append(chinese);
        sb.append(", voice=").append(voice);
        sb.append(", source=").append(source);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}