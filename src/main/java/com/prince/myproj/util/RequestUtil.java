package com.prince.myproj.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestUtil {
    public static JSONObject getRequestBody(HttpServletRequest request) throws IOException {
        InputStreamReader isr = new InputStreamReader(request.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer("");
        String line = null;
        while((line=br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        return JSONObject.parseObject(sb.toString());
    }

}
