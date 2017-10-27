package com.prince.myproj.brain.models;

public class BrainQuestionModel {
    private Long id;
    private String question;
    private String answers;
    private Integer answer;
    private Integer difficult;
    private String cate;
    private String createTime;

    public Integer getAnswer() {
        return answer;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public Long getId() {
        return id;
    }

    public String getAnswers() {
        return answers;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getCate() {
        return cate;
    }
}
