package com.haibin.calendarviewproject.coming;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarviewproject.Article;
import com.haibin.calendarviewproject.ArticleAdapter;
import com.haibin.calendarviewproject.R;
import com.haibin.calendarviewproject.base.activity.BaseActivity;
import com.haibin.calendarviewproject.group.GroupItemDecoration;
import com.haibin.calendarviewproject.group.GroupRecyclerView;
import com.haibin.calendarviewproject.util.AuntUtil;
import com.haibin.calendarviewproject.util.CalendarUtil;
import com.haibin.calendarviewproject.util.Constant;
import com.haibin.calendarviewproject.util.MLog;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ansen
 * @create time 2022/7/5
 */
public class ComingActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener, CalendarView.OnMonthChangeListener{

    TextView tvDate,mTextCurrentDay;
    private CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    CalendarLayout mCalendarLayout;
    private GroupRecyclerView mRecyclerView;

    private Map<String, Calendar> map = new HashMap<>();

    public static void show(Context context) {
        context.startActivity(new Intent(context, ComingActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coming;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setStatusBarDarkMode();
        tvDate = findViewById(R.id.tv_date);
        mRelativeTool = findViewById(R.id.rl_tool);
        mCalendarView =  findViewById(R.id.calendarView);
        mTextCurrentDay =  findViewById(R.id.tv_current_day);

        findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });

        mCalendarLayout = findViewById(R.id.calendarLayout);
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnMonthChangeListener(this);
        mCalendarView.setOnNextMonthChangeListener(onNextMonthChangeListener);

        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        updateDate(mCalendarView.getCurYear(),mCalendarView.getCurMonth(),mCalendarView.getCurDay());

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(this));
        mRecyclerView.notifyDataSetChanged();

        showAunt(mCalendarView.getCurYear(),mCalendarView.getCurMonth());

        GregorianCalendar calendar=new GregorianCalendar(mCalendarView.getCurYear(), mCalendarView.getCurMonth()-1, 1);
        calendar.add(GregorianCalendar.MONTH,1);
//        MLog.i("年:"+calendar.get(GregorianCalendar.YEAR)+" 月:"+(calendar.get(GregorianCalendar.MONTH)+1));
        showAunt(calendar.get(GregorianCalendar.YEAR),calendar.get(GregorianCalendar.MONTH)+1);

        calendar.add(GregorianCalendar.MONTH,-2);
//        MLog.i("年:"+calendar.get(GregorianCalendar.YEAR)+" 月:"+(calendar.get(GregorianCalendar.MONTH)+1));
        showAunt(calendar.get(GregorianCalendar.YEAR),calendar.get(GregorianCalendar.MONTH)+1);
    }

    @Override
    protected void initData() {

    }

//    @Override
//    protected void initData() {
//        int year = mCalendarView.getCurYear();
//        int month = mCalendarView.getCurMonth();
//
//        Map<String, Calendar> map = new HashMap<>();
//
//        map.put(getSchemeCalendar(year, month, 9).toString(),getSchemeCalendar(year, month, 9));
//        map.put(getSchemeCalendar(year, month, 10).toString(),getSchemeCalendar(year, month, 10));
//        map.put(getSchemeCalendar(year, month, 11).toString(),getSchemeCalendar(year, month, 11));
//        map.put(getSchemeCalendar(year, month, 20).toString(),getSchemeCalendar(year, month, 20));
//        map.put(getSchemeCalendar(year, 6, 11).toString(),getSchemeCalendar(year, 6, 11));
//
//        //此方法在巨大的数据量上不影响遍历性能，推荐使用
//        mCalendarView.setSchemeDate(map);
//    }

    private Calendar getSchemeCalendar(int year, int month, int day) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);

        if(year == 2022 && month== 7 && day == 9){
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.START));
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.SEX));
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.FLOW));

            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.FLOW));

            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.SYMPTOM));
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.MOOD));
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.TEMPERATURE));
        }

        if(year == 2022 && month== 7 && day == 10){
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.AUNT));

            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.SEX));
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.DYSMENORRHEA));
        }

        if(year == 2022 && month== 7 && day == 11){
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.END));
        }

        if(year == 2022 && month== 7 && day == 20){
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.MOOD));
        }

        if(year == 2022 && month== 6 && day == 11){
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.AUNT));

            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.SEX));
            calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.DYSMENORRHEA));
        }
        return calendar;
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        updateDate(calendar.getYear(),calendar.getMonth(),calendar.getDay());
        Log.e("onDateSelected", "  -- " + calendar.getYear() +
                "  --  " + calendar.getMonth() +
                "  -- " + calendar.getDay() +
                "  --  " + isClick + "  --   " + calendar.getScheme());
    }

    @Override
    public void onYearChange(int year) {
//        Log.i("ansen","onYearChange year:"+year);
//        mTextMonthDay.setText(String.valueOf(year));
//        tvDate.setText(mCalendarView.getCurYear()+"年"+mCalendarView.getCurMonth()+"月"+mCalendarView.getCurDay()+"日");
    }

    private CalendarView.OnMonthChangeListener onNextMonthChangeListener=new CalendarView.OnMonthChangeListener() {
        @Override
        public void onMonthChange(int year, int month) {
            showAunt(year,month);
        }
    };

    @Override
    public void onMonthChange(int year, int month) {
        Log.i("ansen","onMonthChange year:"+year+" month:"+month);
    }

    private void updateDate(int year, int month, int day){
        tvDate.setText(year+"年"+month+"月"+day+"日");
    }

    public void showAunt(int year,int month){
        MLog.i("showAunt year:"+year+" month:"+month);
        int[] days=AuntUtil.calculationAunt(year,month);
        for(int i=0;i<days.length;i++){
            int type=days[i];
            Calendar calendar = new Calendar(year,month,i+1);
            if(type==Constant.CalendarShowType.AUNT || type==Constant.CalendarShowType.FORESEE_AUNT){//大姨妈
                calendar.setSchemeColor(R.color.white);
                calendar.addScheme(new Calendar.Scheme(type));
            }else if(type==Constant.CalendarShowType.SECURITY){//安全期
                calendar.setSchemeColor(R.color.security);
            }else if(type==Constant.CalendarShowType.OVULATION){//排卵日
                calendar.setSchemeColor(R.color.period_ovulation);
                calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.OVULATION));
            }else if(type==Constant.CalendarShowType.PERIOD_OVULATION){//排卵期
                calendar.setSchemeColor(R.color.period_ovulation);
            }

//            if(i==8){
//                calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.START));
//                calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.SEX));
//                calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.FLOW));
//
//                calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.SYMPTOM));
//                calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.MOOD));
//                calendar.addScheme(new Calendar.Scheme(Constant.CalendarShowType.TEMPERATURE));
//            }

            map.put(calendar.toString(),calendar);
//            MLog.i("年:"+year+" 月:"+month+" 日:"+(i+1)+" color:"+calendar.getSchemeColor());
        }
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);
    }
}