package com.haibin.calendarviewproject.util;

/**
 * @author Ansen
 * @create time 2022/7/7
 */
public class AuntUtil {
    public static int[] calculationAunt(int selectYear,int selectMonth){
        int year=2022,month=7,day=8,menstruationCycle=28,periodDay=5;
        boolean isCurrentMonth=selectMonth==month;
        int daysOfMonth = CalendarUtil.getSumOfDayInMonthForGregorianByMonth(selectYear,selectMonth);//当前月的天数
        int ddday = day - 1;

        if (!isCurrentMonth) {//不是当前月
            int lastDaysOfMonth = CalendarUtil.getSumOfDayInMonthForGregorianByMonth(selectYear, selectMonth - 1); //上一个月的总天数
            ddday=ddday + menstruationCycle - lastDaysOfMonth;
            if (ddday < 0){
                ddday = ddday + menstruationCycle;
            }
        }

        int[] tag = new int[daysOfMonth];
        for (int i = 0; i < tag.length; i++) {
            int a = i - ddday;
            int value = 0;
            if (a < 0) {
                value = (menstruationCycle - (-a) % menstruationCycle) % menstruationCycle;
            } else {
                value = a % menstruationCycle;
            }

            String result="";
            if (value < periodDay) {
                tag[i] = Constant.CalendarShowType.AUNT;//大姨妈
                result="月经期";
            } else{
                tag[i] = Constant.CalendarShowType.SECURITY;//安全期
                result="安全期";
            }
            if (menstruationCycle - 19 <= value && value <= menstruationCycle- 10) {
                if (value == menstruationCycle- 14) {
                    tag[i] = Constant.CalendarShowType.OVULATION;//排卵日
                    result="排卵日";
                } else {
                    tag[i] = Constant.CalendarShowType.PERIOD_OVULATION;//排卵期
                    result="排卵期";
                }
            }
            MLog.i(selectYear+"年"+selectMonth+"月"+(i+1)+"日 属于:"+result);
        }
        return tag;
    }
}