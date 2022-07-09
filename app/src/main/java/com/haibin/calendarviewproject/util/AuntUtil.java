package com.haibin.calendarviewproject.util;

import java.util.GregorianCalendar;

/**
 * 计算方式，参考:https://www.pcbaby.com.cn/tools/aqq/?x=28-5-2022-7-9
 * @author Ansen
 * @create time 2022/7/7
 */
public class AuntUtil {
    public static int[] calculationAunt(int year,int month){
        long mensesCyc = 28;//两次月经开始日大概相隔
        long myavgMenses=5;//经期长度
        long lastTime = 1657296000000L;//最后一次月经的开始时间

        int days = CalendarUtil.getSumOfDayInMonthForGregorianByMonth(year,month);//当前月的天数

        int[] tag = new int[days];
        int secondUnit = 24 * 60 * 60 * 1000;
        for (int i = 1; i <= days; i++) {
            long currentTime=new GregorianCalendar(year,month-1,i,0,0,0).getTimeInMillis();
            int dayDiff= (int) ((currentTime - lastTime)/secondUnit);
            long dayRemainder = (dayDiff % mensesCyc + mensesCyc) % mensesCyc;
            String result="";
            if (dayRemainder >= 0 && dayRemainder <= (myavgMenses-1)) {
                if(currentTime > CalendarUtil.getTodayTimeMillis()){
                    tag[i-1] = Constant.CalendarShowType.FORESEE_AUNT;//推算大姨妈
                }else{
                    tag[i-1] = Constant.CalendarShowType.AUNT;//大姨妈
                }
            }
            if (dayRemainder >= myavgMenses && dayRemainder <= (mensesCyc - 20)) {
                tag[i-1] = Constant.CalendarShowType.SECURITY;//安全期
            }
            if (dayRemainder >= (mensesCyc - 19) && dayRemainder <= (mensesCyc - 10)) {
                if(dayRemainder == 14){
                    tag[i-1] = Constant.CalendarShowType.OVULATION;//排卵日
                }else{
                    tag[i-1] = Constant.CalendarShowType.PERIOD_OVULATION;//排卵期
                }
            }
            if (dayRemainder >= (mensesCyc - 9) && dayRemainder <= (mensesCyc - 1)) {
                tag[i-1] = Constant.CalendarShowType.SECURITY;//安全期
            }
//            System.out.println("相差天数:"+dayDiff + " 年:"+year+" 月:"+month+" 日:"+i+" "+result+" dayRemainder："+dayRemainder);
        }
        return tag;
    }
}