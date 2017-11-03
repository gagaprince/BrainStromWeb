package com.prince.myproj.util;

import java.io.*;

public class FileUtil {
    public static String getContentFromFile(String path){
        File f = new File(path);
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(f)),"utf-8"));
            String line = null;
            while((line=br.readLine())!=null){
                sb.append(line).append('\n');
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
        return sb.toString();
    }
}
