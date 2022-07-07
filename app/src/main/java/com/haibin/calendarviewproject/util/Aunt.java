package com.haibin.calendarviewproject.util;

/**
 * @author Ansen
 * @create time 2022/7/7
 */
public class Aunt {
    public void calculationAunt(int selectYear,int selectMonth){
        int year=2022,month=7,day=7,
                menstruationCycle=28,periodDay=5;
        boolean isCurrentMonth=selectMonth==month;

//        boolean isLeapyear = ChineseCalendar.isGregorianLeapYear(selectYear);//是否为闰年
        int daysOfMonth = CalendarUtil.getSumOfDayInMonthForGregorianByMonth(selectYear,selectMonth);//当前月的天数
        int ddday = day - 1;

        if (!isCurrentMonth) {//不是当前月
            int lastDaysOfMonth = CalendarUtil.getSumOfDayInMonthForGregorianByMonth(selectYear, selectMonth - 1); //上一个月的总天数
            ddday=ddday + menstruationCycle - lastDaysOfMonth;
            if (ddday < 0) {
                ddday = ddday + menstruationCycle;
            }
        }

//        Log.i("ansen","ddday:"+ddday+" lastDaysOfMonth:"+lastDaysOfMonth);

        int[] tag = new int[daysOfMonth];
        for (int i = 0; i < tag.length; i++) {
            int a = i - ddday;
//            Log.i("ansen","i:"+i+" ddday:"+ddday+" a:"+a);
            int value = 0;
            if (a < 0) {
                value = (menstruationCycle - (-a) % menstruationCycle) % menstruationCycle;
            } else {
                value = a % menstruationCycle;
            }

            String result="";
            if (value < periodDay) {
                tag[i] = Constant.MenstruationType.MENSTRUAL_PERIOD;//月经期
//                iView.getCurrentSelectDateList().add(new LocalDate(year+"-"+month+"-"+day));
//                MLog.i("添加月经期 年:"+localDate.getYear()+" 月:"+localDate.getMonthOfYear()+" 日:"+localDate.getDayOfMonth());
                result="月经期";


            } else{
                tag[i] = Constant.MenstruationType.SAFETY_PERIOD;//安全期
                result="安全期";
            }
            if (menstruationCycle - 19 <= value && value <= menstruationCycle- 10) {
                if (value == menstruationCycle- 14) {
                    tag[i] = Constant.MenstruationType.OVULATION;//排卵日
                    result="排卵日";
                } else {
                    tag[i] = Constant.MenstruationType.PERIOD_OVULATION;//排卵期
                    result="排卵期";
                }
            }
            System.out.println(selectYear+"年"+selectMonth+"月"+(i+1)+"日 属于:"+result);
//            System.out.println("当前月:"+selectMonth+" 当前日:"+(i+1)+" 结果:"+result);
        }
    }
}