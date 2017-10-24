package com.prince.myproj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String now(){
        return format(new Date());
    }

    public static String format(Date date){
        return format(date,null);
    }

    public static String format(Date date,String formatStr){
        if(formatStr==null){
            formatStr = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        return formatter.format(date);
    }
}
