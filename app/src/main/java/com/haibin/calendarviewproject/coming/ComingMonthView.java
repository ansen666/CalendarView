package com.haibin.calendarviewproject.coming;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Log;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;
import com.haibin.calendarviewproject.R;
import com.haibin.calendarviewproject.util.Constant;

import java.util.List;

/**
 * 演示一个变态需求的月视图
 * Created by huanghaibin on 2018/2/9.
 */

public class ComingMonthView extends MonthView {
    private Paint auntPaint = new Paint();
    private Paint auntPaintStroke = new Paint();


//    private int mRadius;

    /**
     * 自定义魅族标记的文本画笔
     */
    private Paint mTextPaint = new Paint();

    /**
     * 今天的背景色
     */
    private Paint mCurrentDayPaint = new Paint();

    private int itemPadding,selectItemPadding;
    private int paintheight,corners,insideCorners;

    public ComingMonthView(Context context) {
        super(context);

        auntPaint.setAntiAlias(true);
        auntPaint.setStyle(Paint.Style.FILL);
        auntPaint.setColor(getResources().getColor(R.color.mainColor));

//        auntPaintStroke.setAntiAlias(true);
        auntPaintStroke.setStyle(Paint.Style.STROKE);
        auntPaintStroke.setStrokeWidth(dipToPx(context, 1.5F));
        auntPaintStroke.setColor(getResources().getColor(R.color.mainColor));

        mTextPaint.setTextSize(dipToPx(context, 8));
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(true);

        mCurrentDayPaint.setAntiAlias(true);
        mCurrentDayPaint.setStyle(Paint.Style.FILL);
        mCurrentDayPaint.setColor(0xFFeaeaea);

        itemPadding=dipToPx(context, 1);
        selectItemPadding=dipToPx(context, 4);
        paintheight=measureHeight(mCurMonthTextPaint);
        corners=dipToPx(context, 5);
        insideCorners=dipToPx(context, 4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mItemHeight = (getWidth() - getCalendarPaddingLeft() - getCalendarPaddingRight()) / 7;
        super.onDraw(canvas);
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
//        if(hasScheme){
//            List<Calendar.Scheme> schemes = calendar.getSchemes();
//            for(int i=0;i<schemes.size();i++){
//                Calendar.Scheme scheme=schemes.get(i);
//
//                if(scheme.getType() == Constant.CalendarShowType.START
//                        ||scheme.getType() == Constant.CalendarShowType.END || scheme.getType() == Constant.CalendarShowType.AUNT){//开始大姨妈
//                    Log.i("ansen","画矩形 onDrawScheme x:"+x+" y:"+y);

//                }
//            }
//        }

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

        mCurMonthTextPaint.setColor(getResources().getColor(R.color.pregnant_up));

        if(isSelected){
            RectF rectStroke = new RectF(x+itemPadding*2, y+itemPadding*2, x + mItemWidth - itemPadding*2, y + mItemHeight -itemPadding*2);
            canvas.drawRoundRect(rectStroke, corners, corners, auntPaintStroke);
        }

        if(hasScheme){//
            List<Calendar.Scheme> schemes = calendar.getSchemes();
            Log.i("ansen","画矩形 onDrawScheme x:"+x+" y:"+y);

            for(int i=0;i<schemes.size();i++){
                Calendar.Scheme scheme=schemes.get(i);

                if(scheme.getType() == Constant.CalendarShowType.START || scheme.getType() == Constant.CalendarShowType.END || scheme.getType() == Constant.CalendarShowType.AUNT){//开始大姨妈
                    mCurMonthTextPaint.setColor(getResources().getColor(R.color.white));
                    if(isSelected){
                        RectF rect = new RectF(x+selectItemPadding, y+selectItemPadding, x + mItemWidth - selectItemPadding, y + mItemHeight -selectItemPadding);
                        canvas.drawRoundRect(rect, insideCorners, insideCorners, auntPaint);
                    }else{
                        RectF rect = new RectF(x+itemPadding, y+itemPadding, x + mItemWidth - itemPadding, y + mItemHeight -itemPadding);
                        canvas.drawRoundRect(rect, corners, corners, auntPaint);
                    }

                }
            }
        }

        canvas.drawText(String.valueOf(calendar.getDay()), cx, y + paintheight/2 + mItemHeight/2,mCurMonthTextPaint);
    }

    public static int measureHeight(Paint paint) {
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int textHeight = ~fm.top - (~fm.top - ~fm.ascent) - (fm.bottom - fm.descent);
        return textHeight;
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
