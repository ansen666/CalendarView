package com.haibin.calendarviewproject.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CalendarUtil {
    /**
     * 获取公历year年month月的天数
     * @param year 年
     * @param month 月，从1开始计数
     * @return 月份包含的天数
     */
    public static int getSumOfDayInMonthForGregorianByMonth(int year, int month){
        return new GregorianCalendar(year, month, 0).get(Calendar.DATE);
    }

    /**
     * @return 今天凌晨时间戳
     */
    public static long getTodayTimeMillis(){
        long dayTime=24 * 60 * 60 * 1000L;
        long nowTime =System.currentTimeMillis();
        long todayTime =nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (dayTime));
        return todayTime;
    }
}
