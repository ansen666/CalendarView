package com.haibin.calendarviewproject.coming;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Log;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarUtil;
import com.haibin.calendarview.MonthView;
import com.haibin.calendarviewproject.R;
import com.haibin.calendarviewproject.util.Constant;
import com.haibin.calendarviewproject.util.DisplayHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示一个变态需求的月视图
 * Created by huanghaibin on 2018/2/9.
 */

public class ComingMonthView extends MonthView {
    private Paint auntPaint = new Paint();
    private Paint auntPaintStroke = new Paint();

    private Paint splitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//分割线

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

        splitPaint.setStyle(Paint.Style.STROKE);
        splitPaint.setStrokeWidth(dipToPx(context, 0.5f));
        splitPaint.setColor(0x88efefef);

        mTextPaint.setTextSize(dipToPx(context, 14));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(true);

        mCurrentDayPaint.setAntiAlias(true);
        mCurrentDayPaint.setStyle(Paint.Style.FILL);
        mCurrentDayPaint.setColor(0xFFeaeaea);

        itemPadding=dipToPx(context, 1);
        selectItemPadding=dipToPx(context, 4);
        paintheight=measureHeight(mTextPaint);
        corners=dipToPx(context, 5);
        insideCorners=dipToPx(context, 4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        mItemHeight = (getWidth() - getCalendarPaddingLeft() - getCalendarPaddingRight()) / 7;
//        Log.i("ansen","重新获取Item高度:"+mItemHeight);
//        Log.i("ansen","控件宽度:"+ DisplayHelper.getWidthPixels());
        super.onDraw(canvas);
    }


//    @Override
//    public void updateItemHeight() {
////        super.updateItemHeight();
//        this.mItemHeight = (DisplayHelper.getWidthPixels() - getCalendarPaddingLeft() - getCalendarPaddingRight()) / 7;
//        Paint.FontMetrics metrics = mCurMonthTextPaint.getFontMetrics();
//        mTextBaseLine = mItemHeight / 2 - metrics.descent + (metrics.bottom - metrics.top) / 2;
//        mHeight = CalendarUtil.getMonthViewHeight(mYear, mMonth, mItemHeight, getWeekStartWith(),getMonthViewShowMode());
//        Log.i("ansen","ComingMonthView updateItemHeight 获取Item高度:"+mItemHeight+" 总高度:"+mHeight);
//    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        return true;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        canvas.drawLine(x, y+mItemHeight, x + mItemWidth, y + mItemHeight-itemPadding, splitPaint);
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        int top = y - mItemHeight / 6;

        mTextPaint.setColor(getResources().getColor(R.color.pregnant_up));

        if(isSelected){
            RectF rectStroke = new RectF(x+itemPadding*2, y+itemPadding*2, x + mItemWidth - itemPadding*2, y + mItemHeight -itemPadding*2);
            canvas.drawRoundRect(rectStroke, corners, corners, auntPaintStroke);
        }

        if(hasScheme){//
            drawScheme(canvas,calendar,x,y,isSelected);
        }
//        Log.i("ansen","onDrawText 年:"+calendar.getYear()+" 月:"+calendar.getMonth()+" 日:"+calendar.getDay());
        canvas.drawText(String.valueOf(calendar.getDay()), cx, y + paintheight/2 + mItemHeight/2,mTextPaint);
    }

    private void drawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isSelected){
        List<Calendar.Scheme> schemes = calendar.getSchemes();
//        Log.i("ansen","画矩形 onDrawScheme x:"+x+" y:"+y);

        List<Integer> topList=new ArrayList<>();
        List<Integer> bottomList=new ArrayList<>();

        for(int i=0;i<schemes.size();i++){
            Calendar.Scheme scheme=schemes.get(i);

            if(scheme.getType() == Constant.CalendarShowType.START || scheme.getType() == Constant.CalendarShowType.END || scheme.getType() == Constant.CalendarShowType.AUNT){//开始大姨妈
                mTextPaint.setColor(getResources().getColor(R.color.white));
                if(isSelected){
                    RectF rect = new RectF(x+selectItemPadding, y+selectItemPadding, x + mItemWidth - selectItemPadding, y + mItemHeight -selectItemPadding);
                    canvas.drawRoundRect(rect, insideCorners, insideCorners, auntPaint);
                }else{
                    RectF rect = new RectF(x+itemPadding, y+itemPadding, x + mItemWidth - itemPadding, y + mItemHeight -itemPadding);
                    canvas.drawRoundRect(rect, corners, corners, auntPaint);
                }

                if(scheme.getType() == Constant.CalendarShowType.START){
                    topList.add(R.mipmap.icon_cl_start);
                }else if(scheme.getType() == Constant.CalendarShowType.END){
                    topList.add(R.mipmap.icon_cl_end);
                }
            }else if(scheme.getType() == Constant.CalendarShowType.SEX){
                topList.add(R.mipmap.icon_cl_sex);
            }else if(scheme.getType() == Constant.CalendarShowType.FLOW){
                topList.add(R.mipmap.icon_cl_flow);
            }else if(scheme.getType() == Constant.CalendarShowType.SYMPTOM){
                bottomList.add(R.mipmap.icon_cl_symptom);
            }else if(scheme.getType() == Constant.CalendarShowType.MOOD){
                bottomList.add(R.mipmap.icon_cl_mood);
            }else if(scheme.getType() == Constant.CalendarShowType.TEMPERATURE){
                bottomList.add(R.mipmap.icon_cl_temperature);
            }else if(scheme.getType() == Constant.CalendarShowType.HABIT){
                bottomList.add(R.mipmap.icon_cl_habit);
            }
        }

        for(int i=0;i<topList.size();i++){
            if(i>2){
                continue;
            }
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),topList.get(i));
            int xLocation = x + mItemWidth / 2;
            if(i==1){
                xLocation = xLocation - bitmap.getWidth() - dipToPx(getContext(), 1);
            }else if(i==2){
                xLocation = xLocation + bitmap.getWidth() + dipToPx(getContext(), 1);
            }
            canvas.drawBitmap(bitmap,xLocation-bitmap.getWidth()/2,y+dipToPx(getContext(), 4),mTextPaint);
        }

        for(int i=0;i<bottomList.size();i++){
            if(i>2){
                continue;
            }
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),bottomList.get(i));
            int xLocation = x + mItemWidth / 2;
            if(i==1){
                xLocation = xLocation - bitmap.getWidth() - dipToPx(getContext(), 1);
            }else if(i==2){
                xLocation = xLocation + bitmap.getWidth() + dipToPx(getContext(), 1);
            }
            canvas.drawBitmap(bitmap,xLocation-bitmap.getWidth()/2,y+mTextBaseLine-dipToPx(getContext(), 2),mTextPaint);
        }
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
