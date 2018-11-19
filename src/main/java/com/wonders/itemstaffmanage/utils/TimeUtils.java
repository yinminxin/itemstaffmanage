package com.wonders.itemstaffmanage.utils;

import java.util.Calendar;

public class TimeUtils {

    /**
     * 获取当前年份
     * @return
     */
    public static int getYear(){
        return getCalendar().get(Calendar.YEAR);
    }

    /**
     * 获取当前时间是当年的第几周
     * @return
     */
    public static int getWeekOfYear(){
        return getCalendar().get(Calendar.WEEK_OF_YEAR);
    }

    public static Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setTimeInMillis(System.currentTimeMillis());//获得当前的时间戳
        return calendar;
    }
}
