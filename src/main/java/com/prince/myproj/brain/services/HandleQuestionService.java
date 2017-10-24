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
                    handleByQuestionAndAnswers(questionStr,answerStr,cate);
                }else{
                    if(line.endsWith(":")||line.endsWith("：")){
                        cate = line.substring(0,line.length()-1);
                        logger.info("分类:"+cate);
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
        /*BrainQuestionModel brainQuestionModel = new BrainQuestionModel();
        brainQuestionModel.setQuestion("这是一个测试的问题");
        brainQuestionModel.setAnswers("答案1|答案2|答案3|答案4");
        brainQuestionModel.setAnswer(1);
        brainQuestionModel.setCate("测试");
        brainQuestionModel.setDifficult(1);
        brainQuestionModel.setCreateTime(DateUtil.now());
        brainQuestionDao.save(brainQuestionModel);*/
    }
    private void handleByQuestionAndAnswers(String question,String answers,String cate){
        String answerCode = question.substring(question.length()-1);
        question = question.substring(1,question.length()-1).trim();
//        logger.info("问题："+question);
//        logger.info("答案选项："+answerCode);
        String pattern = "(A[\\.|、]?.*)(B[\\.|、]?.*)(C[\\.|、]?.*)(D[\\.|、]?.*)?";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(answers);
        if(m.find()){
//            logger.info(m.group(0));
//            logger.info(m.group(1));
//            logger.info(m.group(2));
//            logger.info(m.group(3));
//            logger.info(m.group(4));
        }else {
            logger.info("没有匹配到："+answers);
        }

    }
}
