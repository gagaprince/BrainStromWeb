package com.prince.myproj.common.bean;

import com.prince.myproj.common.enums.ErrorCode;

public class AjaxBean {
    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setError(ErrorCode c){
        this.setMsg(c.getDes());
        this.setCode(c.getCode());
    }
}
