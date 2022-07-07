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
import com.haibin.calendarviewproject.colorful.ColorfulActivity;
import com.haibin.calendarviewproject.custom.CustomActivity;
import com.haibin.calendarviewproject.group.GroupItemDecoration;
import com.haibin.calendarviewproject.group.GroupRecyclerView;
import com.haibin.calendarviewproject.index.IndexActivity;
import com.haibin.calendarviewproject.simple.SimpleActivity;
import com.haibin.calendarviewproject.util.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ansen
 * @create time 2022/7/5
 */
public class ComingActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener, CalendarView.OnMonthChangeListener,
        View.OnClickListener {

    TextView tvDate,mTextCurrentDay;
    private CalendarView mCalendarView;

    RelativeLayout mRelativeTool;
    CalendarLayout mCalendarLayout;
    GroupRecyclerView mRecyclerView;

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

        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        updateDate(mCalendarView.getCurYear(),mCalendarView.getCurMonth(),mCalendarView.getCurDay());
    }

    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        Map<String, Calendar> map = new HashMap<>();

        map.put(getSchemeCalendar(year, month, 9).toString(),
                getSchemeCalendar(year, month, 9));
        map.put(getSchemeCalendar(year, month, 10).toString(),
                getSchemeCalendar(year, month, 10));
        map.put(getSchemeCalendar(year, month, 11).toString(),
                getSchemeCalendar(year, month, 11));
        map.put(getSchemeCalendar(year, month, 20).toString(),
                getSchemeCalendar(year, month, 20));

        map.put(getSchemeCalendar(year, 6, 11).toString(),
                getSchemeCalendar(year, 6, 11));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView.setSchemeDate(map);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(this));
        mRecyclerView.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_flyme:
                CustomActivity.show(this);
                break;
            case R.id.ll_simple:
                SimpleActivity.show(this);
                break;
            case R.id.ll_colorful:
                ColorfulActivity.show(this);
                break;
            case R.id.ll_index:
                IndexActivity.show(this);
                break;
        }
    }

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
        Log.i("ansen","onYearChange year:"+year);
//        mTextMonthDay.setText(String.valueOf(year));
//        tvDate.setText(mCalendarView.getCurYear()+"年"+mCalendarView.getCurMonth()+"月"+mCalendarView.getCurDay()+"日");
    }

    @Override
    public void onMonthChange(int year, int month) {
        Log.i("ansen","onMonthChange year:"+year+" month:"+month);
    }

    private void updateDate(int year, int month, int day){
        tvDate.setText(year+"年"+month+"月"+day+"日");
    }
}