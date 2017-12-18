package com.prince.myproj.wx.bean;

public class TemplateMsgBean {
    private String touser;
    private String template_id;
    private String page;
    private String form_id;
    private TemplateMsgDataBean data;
    private String emphasis_keyword;

    public TemplateMsgDataBean getData() {
        return data;
    }

    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }

    public String getForm_id() {
        return form_id;
    }

    public String getPage() {
        return page;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public String getTouser() {
        return touser;
    }

    public void setData(TemplateMsgDataBean data) {
        this.data = data;
    }

    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }
}

