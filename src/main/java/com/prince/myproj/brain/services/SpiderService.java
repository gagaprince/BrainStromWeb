package com.prince.myproj.brain.services;

import com.alibaba.fastjson.JSONObject;
import com.prince.myproj.brain.dao.BrainEnergyModelMapper;
import com.prince.myproj.brain.dao.BrainSentenceModelMapper;
import com.prince.myproj.brain.dao.BrainWordModelMapper;
import com.prince.myproj.brain.models.BrainEnergyModel;
import com.prince.myproj.brain.models.BrainSentenceModel;
import com.prince.myproj.brain.models.BrainWordModel;
import com.prince.myproj.common.bean.AjaxBean;
import com.prince.myproj.common.enums.ErrorCode;
import com.prince.myproj.util.FileUtil;
import com.prince.myproj.util.HttpUtil;
import org.apache.log4j.Logger;
import org.aspectj.weaver.loadtime.Aj;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpiderService {
    public static final Logger logger = Logger.getLogger(SpiderService.class);

    @Autowired
    private BrainWordService brainWordService;

    @Autowired
    private BrainSentenceModelMapper brainSentenceModelMapper;

    @Autowired
    private BrainEnergyModelMapper brainEnergyModelMapper;

    @Autowired
    private BrainWordModelMapper brainWordModelMapper;

    public AjaxBean spiderSentence(){
        AjaxBean ajaxBean = new AjaxBean();

        int size = 1000;
        int now = 0;
        while (true){
            List<BrainWordModel> brainWordModels = brainWordService.findBrainWordModelsBySLAndWordType(now*size,size,null);
            if(brainWordModels.size()==0){
                break;
            }
            spiderSentenceByWords(brainWordModels);
            now++;
        }

        ajaxBean.setError(ErrorCode.SUCCESS);
        return ajaxBean;
    }

    private void spiderSentenceByWords(List<BrainWordModel> brainWordModels){
        for(int i=0;i<brainWordModels.size();i++){
            BrainWordModel brainWordModel = brainWordModels.get(i);
            String word = brainWordModel.getWord();
            try {
                spiderSentenceByWord(word);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void spiderSentenceByWord(String word){
        String spiderUrl = "http://dj.iciba.com/"+word;
        String content = HttpUtil.getContentByUrl(spiderUrl);
        Document doc = Jsoup.parse(content);
        Elements sentenceEles = doc.getElementsByClass("dj_li");
        for(Element sentenceEle:sentenceEles){
            BrainSentenceModel brainSentenceModel = new BrainSentenceModel();
            brainSentenceModel.setKeyWord(word);
            Elements spans = sentenceEle.getElementsByTag("span");
            for(Element span:spans){
                String insentence = span.attr("con");
                if(insentence!=null){
                    if(span.attr("id").startsWith("dj_english")){
                        brainSentenceModel.setEnglish(insentence);
                    }
                    if(span.attr("id").startsWith("dj_chinese")){
                        brainSentenceModel.setChinese(insentence);
                    }
                }
            }
            Elements voiceEles = sentenceEle.getElementsByClass("sound");
            if(voiceEles.size()>0){
                Element voiceEle = voiceEles.get(0);
                String voiceFun = voiceEle.attr("onclick");
                String voice = voiceFun.substring(voiceFun.indexOf("('")+2,voiceFun.indexOf("')"));
                brainSentenceModel.setVoice(voice);
            }
            Elements sourcePs = sentenceEle.getElementsByClass("stc_from");
            if(sourcePs.size()>0) {
                Element sourceP = sourcePs.get(0);
                String source = sourceP.html().replaceAll("<.*?>", "");
                brainSentenceModel.setSource(source);
            }
            logger.info(brainSentenceModel);
            brainSentenceModelMapper.insertSelective(brainSentenceModel);
        }
    }

    public AjaxBean spiderEnergy(){
        AjaxBean ajaxBean = new AjaxBean();

        //爬虫逻辑
        int count = 40;
        for(int i=1;i<=count;i++){
            spiderEnergyByPage(i);
        }

        ajaxBean.setError(ErrorCode.SUCCESS);
        return ajaxBean;
    }

    private void spiderEnergyByPage(int pageNum){
        String pageUrl = "http://news.iciba.com/appv3/wwwroot/ds.php?action=tags&id=10&ob=1&order=2&page="+pageNum+"#nav";
        String content = HttpUtil.getContentByUrl(pageUrl);
        Document doc = Jsoup.parse(content);
        Elements tlenClass = doc.getElementsByClass("t_l_en");
        for(Element tlen:tlenClass){
            Element link = tlen.getElementsByTag("a").get(0);
            spiderEnergyDetailByLink(link.attr("href"));
        }
    }

    private void spiderEnergyDetailByLink(String link){
        String sid = link.substring(link.indexOf("-")+1,link.lastIndexOf("."));
        logger.info(sid);
        //http://sentence.iciba.com/index.php?callback=jQuery190014991332056055917_1509624601237&c=dailysentence&m=getdetail&sid=71&_=1509624601241
        String url = "http://sentence.iciba.com/index.php?c=dailysentence&m=getdetail&sid="+sid+"&_=1509624601241";
        String content = HttpUtil.getContentByUrl(url);

        JSONObject energyJson = JSONObject.parseObject(content);
        String english = energyJson.getString("content");
        String chinese = energyJson.getString("note");
        String voice = energyJson.getString("tts");
        String source = energyJson.getString("translation");
        Date now = new Date();
        BrainEnergyModel brainEnergyModel = new BrainEnergyModel();
        brainEnergyModel.setEnglish(english);
        brainEnergyModel.setChinese(chinese);
        brainEnergyModel.setVoice(voice);
        brainEnergyModel.setSource(source);
        brainEnergyModel.setCreateTime(now);

        brainEnergyModelMapper.insertSelective(brainEnergyModel);
    }

    public AjaxBean spiderWordType(){
        AjaxBean ajaxBean = new AjaxBean();

        spiderCET4WordType();
        spiderCET6WordType();
        spiderGREWordType();

        ajaxBean.setError(ErrorCode.SUCCESS);
        return ajaxBean;
    }

    private void spiderCET4WordType(){
        //抓取4级词汇 更新数据库 from file
        logger.info("开始更新4级词汇");
        String cet4Path = "/Users/wangzidong/Downloads/cet4.txt";
        String content = FileUtil.getContentFromFile(cet4Path);
        String[] lines = content.split("\n");
        for(String line:lines){
            int index = line.indexOf("/ ");
            BrainWordModel brainWordModel = excuteWordByLineIndex(line,index);
            if (brainWordModel != null) {
                brainWordModel.setCet4(1);
                brainWordModelMapper.updateByPrimaryKeySelective(brainWordModel);
            }
        }

    }
    private void spiderCET6WordType(){
        //抓取6级词汇 更新数据库 from file
        logger.info("开始更新6级词汇");
        String cet6Path = "/Users/wangzidong/Downloads/cet6.txt";
        String content = FileUtil.getContentFromFile(cet6Path);
        String[] lines = content.split("\n");
        for(String line:lines){
            int index = line.indexOf(" ");
            BrainWordModel brainWordModel = excuteWordByLineIndex(line,index);
            if (brainWordModel != null) {
                brainWordModel.setCet6(1);
                brainWordModelMapper.updateByPrimaryKeySelective(brainWordModel);
            }
        }
    }

    private void spiderGREWordType(){
        //抓取gre词汇 更新数据库 from file
        logger.info("开始更新gre词汇");
        String grePath = "/Users/wangzidong/Downloads/gre.txt";
        String content = FileUtil.getContentFromFile(grePath);
        String[] lines = content.split("\n");
        for(String line:lines){
            int index = line.replaceAll("\\s+"," ").indexOf(" ");
            BrainWordModel brainWordModel = excuteWordByLineIndex(line,index);
            if (brainWordModel != null) {
                brainWordModel.setGre(1);
                brainWordModelMapper.updateByPrimaryKeySelective(brainWordModel);
            }
        }
    }

    private BrainWordModel excuteWordByLineIndex(String line ,int index){
        if(index!=-1){
            String word = line.substring(0,index);
            logger.info(word);
            //从数据库中找到这个词
            BrainWordModel brainWordModel = brainWordModelMapper.selectByWord(word);
            logger.info(brainWordModel);
            return brainWordModel;
        }
        return null;
    }
}
