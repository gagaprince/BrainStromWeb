package com.prince.myproj.brain.models;

public class BrainUserModel {
    private Long id;
    private String openId;
    private String nickName;
    private Integer gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private String phone;
    private String createTime;
    private String updateTime;
    private String sessionKey;


    public String getSessionKey() {
        return sessionKey;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public String getOpenId() {
        return openId;
    }

    public Long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getGender() {
        return gender;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
