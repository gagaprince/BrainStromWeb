package com.prince.myproj.common.enums;

public enum ErrorCode {
    SUCCESS("成功",0),
    WX_CODE_ERROR("无效的code",1000),
    ENERGY_SELECT_ERROR("energy 查询失败",1001);
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
