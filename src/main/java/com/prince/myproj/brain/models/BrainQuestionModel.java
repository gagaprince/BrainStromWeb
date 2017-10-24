package com.prince.myproj.brain.models;

public class BrainQuestionModel {
    private long id;
    private String question;
    private String answers;
    private int answer;
    private int difficult;
    private String cate;
    private String createTime;

    public int getAnswer() {
        return answer;
    }

    public int getDifficult() {
        return difficult;
    }

    public long getId() {
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

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public void setId(long id) {
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
