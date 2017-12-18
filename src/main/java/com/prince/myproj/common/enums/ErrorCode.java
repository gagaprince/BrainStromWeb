package com.prince.myproj.common.enums;

public enum ErrorCode {
    SUCCESS("成功",0),
    WX_CODE_ERROR("无效的code",1000),
    ENERGY_SELECT_ERROR("energy 查询失败",1001),
    WORD_NO_SEARCH_RESULT("没有查询到结果",1002),
    WORD_SEARCH_ERROR("搜索失败",1003),
    REQUEST_ERROR("获取搜索参数失败",1004),
    GET_TOKEN_ERROR("没有获取到ACCESS_TOKEN",1005),
    ;
    private String des;
    private int code;
    // 构造方法
    private ErrorCode(String des, int code) {
        this.des = des;
        this.code = code;
    }
    public String getDes(){
        return des;
    }

    public int getCode() {
        return code;
    }
}
