package com.haibin.calendarviewproject.coming;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.WeekView;
import com.haibin.calendarviewproject.R;
import com.haibin.calendarviewproject.util.BaseConst;
import com.haibin.calendarviewproject.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示一个变态需求的周视图
 * Created by huanghaibin on 2018/2/9.
 */

public class ComingWeekView extends WeekView {
    private Paint auntPaint = new Paint();
    private Paint auntPaintStroke = new Paint();
    private Paint mTextPaint = new Paint();//文本画笔

    private int itemPadding,selectItemPadding;
    private int corners,insideCorners;

    public ComingWeekView(Context context) {
        super(context);

        auntPaint.setAntiAlias(true);
        auntPaint.setStyle(Paint.Style.FILL);

        auntPaintStroke.setStyle(Paint.Style.STROKE);
        auntPaintStroke.setStrokeWidth(Utils.dipToPx(context, 1.5F));
        auntPaintStroke.setColor(getResources().getColor(R.color.mainColor));

        mTextPaint.setTextSize(Utils.dipToPx(context, 16));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        itemPadding=Utils.dipToPx(context, 1);
        selectItemPadding=Utils.dipToPx(context, 4);
        corners=Utils.dipToPx(context, 5);
        insideCorners=Utils.dipToPx(context, 4);
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, boolean hasScheme) {
        return true;
    }


    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x) {

    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        if(calendar.getSchemeColor()!=0){
            mTextPaint.setColor(getResources().getColor(calendar.getSchemeColor()));
        }

        if(isSelected){
            RectF rectStroke = new RectF(x+itemPadding*2, itemPadding*2, x + mItemWidth - itemPadding*2, mItemHeight -itemPadding*2);
            canvas.drawRoundRect(rectStroke, corners, corners, auntPaintStroke);
        }

        if(hasScheme ){//
            drawScheme(canvas,calendar,x,0,isSelected);
        }
//        Log.i("ansen","onDrawText 年:"+calendar.getYear()+" 月:"+calendar.getMonth()+" 日:"+calendar.getDay());
        canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine,mTextPaint);
    }

    private void drawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isSelected){
        List<Calendar.Scheme> schemes = calendar.getSchemes();
        if(schemes==null||schemes.size()==0){
            return ;
        }

//        MLog.i("画矩形 onDrawScheme x:"+x+" y:"+y);

        List<Integer> topList=new ArrayList<>();
        List<Integer> bottomList=new ArrayList<>();

        for(int i=0;i<schemes.size();i++){
            Calendar.Scheme scheme=schemes.get(i);

            if(scheme.getType() == BaseConst.ShowType.AUNT || scheme.getType() == BaseConst.ShowType.FORESEE_AUNT){//大姨妈
                if(scheme.getType() == BaseConst.ShowType.FORESEE_AUNT){//预测大姨妈
                    auntPaint.setColor(getResources().getColor(R.color.foresee_aunt));
                }else{
                    auntPaint.setColor(getResources().getColor(R.color.aunt));
                }

                if(isSelected){
                    RectF rect = new RectF(x+selectItemPadding, y+selectItemPadding, x + mItemWidth - selectItemPadding, y + mItemHeight -selectItemPadding);
                    canvas.drawRoundRect(rect, insideCorners, insideCorners, auntPaint);
                }else{
                    RectF rect = new RectF(x+itemPadding, y+itemPadding, x + mItemWidth - itemPadding, y + mItemHeight -itemPadding);
                    canvas.drawRoundRect(rect, corners, corners, auntPaint);
                }
            }else if(scheme.getType() == BaseConst.ShowType.START){
                topList.add(R.mipmap.icon_cl_start);
            }else if(scheme.getType() == BaseConst.ShowType.END){
                topList.add(R.mipmap.icon_cl_end);
            }else if(scheme.getType()== BaseConst.ShowType.OVULATION){//排卵日
                topList.add(R.mipmap.icon_cl_ovulation);
            }else if(scheme.getType() == BaseConst.ShowType.SEX){
                topList.add(R.mipmap.icon_cl_sex);
            }else if(scheme.getType() == BaseConst.ShowType.FLOW){
                topList.add(R.mipmap.icon_cl_flow);
            }else if(scheme.getType() == BaseConst.ShowType.SYMPTOM){
                bottomList.add(R.mipmap.icon_cl_symptom);
            }else if(scheme.getType() == BaseConst.ShowType.MOOD){
                bottomList.add(R.mipmap.icon_cl_mood);
            }else if(scheme.getType() == BaseConst.ShowType.TEMPERATURE){
                bottomList.add(R.mipmap.icon_cl_temperature);
            }else if(scheme.getType() == BaseConst.ShowType.HABIT){
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
                xLocation = xLocation - bitmap.getWidth() - Utils.dipToPx(getContext(), 1);
            }else if(i==2){
                xLocation = xLocation + bitmap.getWidth() + Utils.dipToPx(getContext(), 1);
            }
            canvas.drawBitmap(bitmap,xLocation-bitmap.getWidth()/2,y+Utils.dipToPx(getContext(), 4),mTextPaint);
        }

        for(int i=0;i<bottomList.size();i++){
            if(i>2){
                continue;
            }
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),bottomList.get(i));
            int xLocation = x + mItemWidth / 2;
            if(i==1){
                xLocation = xLocation - bitmap.getWidth() - Utils.dipToPx(getContext(), 1);
            }else if(i==2){
                xLocation = xLocation + bitmap.getWidth() + Utils.dipToPx(getContext(), 1);
            }
            canvas.drawBitmap(bitmap,xLocation-bitmap.getWidth()/2,y+mTextBaseLine+bitmap.getHeight()/2-Utils.dipToPx(getContext(), 1),mTextPaint);
        }
    }
}
