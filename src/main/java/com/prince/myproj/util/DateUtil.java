package com.prince.myproj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String now(){
        return format(new Date());
    }

    public static Date today(){
        return new Date();
    }

    public static boolean equalsWithDate(Date date1,Date date2){
        return format(date1,"yyyy-MM-dd").equals(format(date2,"yyyy-MM-dd"));
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

    public static Date parseToDate(String dateStr,String formatStr){
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int disFromDate(Date old, Date now){
        long timedis = now.getTime()-old.getTime();
        return (int)(timedis/24/3600/1000);
    }

    public static int disFromDate(String oldStr,Date now){
        Date old = parseToDate(oldStr,"yyyy-MM-dd");
        return disFromDate(old,now);
    }
}
