package com.haibin.calendarviewproject.util;

/**
 * @author Ansen
 * @create time 2022/7/6
 */
public class BaseConst {
    public static class ShowType {
        public static final int START=1;//开始大姨妈
        public static final int END=2;//结束大姨妈

        public static final int AUNT=3;//大姨妈

        public static final int SEX=4;//性
        public static final int SEX_UNPROTECTED=5;//性（无保护）

        public static final int FLOW=6;//大姨妈流量
        public static final int DYSMENORRHEA=7;//痛经
        public static final int TEMPERATURE=8;//体温
        public static final int MOOD=9;//心情/日记，只要有一项就显示这个icon
        public static final int SYMPTOM=10;//症状
        public static final int HABIT=11;//习惯

        public static final int FORESEE_AUNT=12;//推算大姨妈(未来的日子)

        public static final int OVULATION=13;//排卵日
        public static final int PERIOD_OVULATION=14;//排卵期

        public static final int SECURITY=15;//安全期

    }

    //大姨妈类型
//    public static class MenstruationType{
//        public static final int TYPE_NONE= 0;//正常情况
//        public static final int SAFETY_PERIOD = 1;//安全期：受孕几率较小
//        public static final int PERIOD_OVULATION = 2;//排卵期：排卵日及其前5天和后4天为排卵期，此时间段受孕的可能性较大
//        public static final int MENSTRUAL_PERIOD = 3;//月经期：请注意经期卫生
//        public static final int OVULATION = 4;//排卵日
//    }
}