package com.prince.myproj.brain.services;

import com.prince.myproj.brain.dao.BrainQuestionDao;
import com.prince.myproj.brain.models.BrainQuestionModel;
import com.prince.myproj.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HandleQuestionService {

    public static final Logger logger = Logger.getLogger(HandleQuestionService.class);

    public static final String root = "/Users/wangzidong/work/server/BrainStorm/";

    @Autowired
    private BrainQuestionDao brainQuestionDao;


    public void doHandleText(){

        String filePath = root+"data/ku.txt";
        logger.info("读取文件:"+filePath);
        File kuFile = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(kuFile)),"utf-8"));
            String line = null;
            String cate = null;
            while((line=br.readLine())!=null){
                line = line.trim();
                if(line.startsWith("﹒")){
                    String questionStr = line.replaceAll("\\t","").trim();
                    line = br.readLine();
                    if(line==null){
                        break;
                    }
                    String answerStr = line.replaceAll("\\t","").trim();
//                    logger.info("问题："+questionStr);
//                    logger.info("答案："+answerStr);
                    BrainQuestionModel brainQuestionModel = handleByQuestionAndAnswers(questionStr,answerStr,cate);
                    brainQuestionDao.save(brainQuestionModel);
                }else{
                    if(line.endsWith(":")||line.endsWith("：")){
                        cate = line.substring(0,line.length()-1);
//                        logger.info("分类:"+cate);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private BrainQuestionModel handleByQuestionAndAnswers(String question,String answers,String cate){
        String answerCode = question.substring(question.length()-1);
        question = question.substring(1,question.length()-1).trim();
//        logger.info("问题："+question);
//        logger.info("答案选项："+answerCode);
        String pattern = "(A[\\.|．|、]?(.*?))(B[\\.|．|、]?(.*?))(C[\\.|．|、]?(.*?))(D[\\.|．|、]?(.*))";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(answers);
        StringBuffer sb = new StringBuffer("");
        if(m.find()){
//            logger.info(m.group(0));
//            logger.info(m.group(1));
//            logger.info(m.group(2));
//            logger.info(m.group(3));
//            logger.info(m.group(4));
//            logger.info(m.group(5));
//            logger.info(m.group(6));
            sb.append(trimAll(m.group(2))).append("|")
                    .append(trimAll(m.group(4))).append("|")
                    .append(trimAll(m.group(6))).append("|");
            if(m.groupCount()>7){
                sb.append(trimAll(m.group(8)));
            }
        }else {
            logger.info("没有匹配到："+answers);
        }
        BrainQuestionModel brainQuestionModel = new BrainQuestionModel();
        brainQuestionModel.setQuestion(question);
        brainQuestionModel.setAnswers(trimAll(sb.toString()));
        brainQuestionModel.setAnswer(parseAnswerNum(answerCode));
        brainQuestionModel.setCate(cate);
        brainQuestionModel.setDifficult(1);
        brainQuestionModel.setCreateTime(DateUtil.now());
        return brainQuestionModel;
    }
    private int parseAnswerNum(String answerCode){
        return answerCode.charAt(0)-65;
    }
    private String trimAll(String s){
        String str = s.trim().replaceAll("　+","");
        return str;
    }
}
