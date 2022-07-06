package com.haibin.calendarviewproject.birthday;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.Log;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

/**
 * 演示一个变态需求的月视图
 * Created by huanghaibin on 2018/2/9.
 */

public class BirthdayMonthView extends MonthView {
    private int mRadius;

    /**
     * 自定义魅族标记的文本画笔
     */
    private Paint mTextPaint = new Paint();


    /**
     * 节日画笔
     */
    private Paint mFestivalTextPaint = new Paint();

    /**
     * 背景圆点
     */
//    private Paint mPointPaint = new Paint();

    /**
     * 今天的背景色
     */
    private Paint mCurrentDayPaint = new Paint();

    /**
     * 圆点半径
     */
//    private float mPointRadius;

    private int mPadding;
//    private float mCircleRadius;

    /**
     * 自定义魅族标记的圆形背景
     */
    private Paint mSchemeBasicPaint = new Paint();

    private float mSchemeBaseLine;

    public BirthdayMonthView(Context context) {
        super(context);

        mTextPaint.setTextSize(dipToPx(context, 8));
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(true);


        mFestivalTextPaint.setColor(0xFFF74949);
        mFestivalTextPaint.setAntiAlias(true);
        mFestivalTextPaint.setTextAlign(Paint.Align.CENTER);


//        mSolarTermTextPaint.setColor(0xff999999);


        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setFakeBoldText(true);
        mSchemeBasicPaint.setColor(Color.WHITE);

        mCurrentDayPaint.setAntiAlias(true);
        mCurrentDayPaint.setStyle(Paint.Style.FILL);
        mCurrentDayPaint.setColor(0xFFeaeaea);

//        mPointPaint.setAntiAlias(true);
//        mPointPaint.setStyle(Paint.Style.FILL);
//        mPointPaint.setTextAlign(Paint.Align.CENTER);
//        mPointPaint.setColor(Color.RED);

//        mCircleRadius = dipToPx(getContext(), 7);
        mPadding = dipToPx(getContext(), 9);

//        mPointRadius = dipToPx(context, 2);

        Paint.FontMetrics metrics = mSchemeBasicPaint.getFontMetrics();
        mSchemeBaseLine = metrics.descent + (metrics.bottom - metrics.top) / 2 + dipToPx(getContext(), 1);


    }


    @Override
    protected void onPreviewHook() {
        mFestivalTextPaint.setTextSize(mCurMonthLunarTextPaint.getTextSize());
        mRadius = Math.min(mItemWidth, mItemHeight) / 11 * 5;
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
        return true;
    }


    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {

    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        int top = y - mItemHeight / 6;

        if (calendar.isCurrentDay() && !isSelected) {
            canvas.drawCircle(cx, cy, mRadius, mCurrentDayPaint);
        }

        if (hasScheme) {
//            canvas.drawCircle(x + mItemWidth - mPadding - mCircleRadius / 2, y + mPadding + mCircleRadius, mCircleRadius, mSchemeBasicPaint);
            mTextPaint.setColor(calendar.getSchemeColor());
            canvas.drawText(calendar.getScheme(), x + mItemWidth - mPadding , y + mPadding + mSchemeBaseLine, mTextPaint);
            Log.i("ansen","getScheme:"+calendar.getScheme()+" 年:"+calendar.getYear()+" 月:"+calendar.getMonth()+" 日:"+calendar.getDay());
        }

//        mSchemeLunarTextPaint.setColor(0xff999999);

        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,mSelectTextPaint);
            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10, mSelectedLunarTextPaint);
        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);

            boolean isFestival=!TextUtils.isEmpty(calendar.getGregorianFestival())||!TextUtils.isEmpty(calendar.getTraditionFestival());

            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10,
                    calendar.isCurrentDay() ? mCurDayLunarTextPaint :
                            calendar.isCurrentMonth() ? isFestival ? mFestivalTextPaint  :
                                    mCurMonthLunarTextPaint : mOtherMonthLunarTextPaint);
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
