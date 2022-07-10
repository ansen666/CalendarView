package com.haibin.calendarviewproject.util;

import android.content.Context;
import android.graphics.Paint;

/**
 * @author Ansen
 * @create time 2022/7/9
 */
public class Utils {
    public static int measureHeight(Paint paint) {
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int textHeight = ~fm.top - (~fm.top - ~fm.ascent) - (fm.bottom - fm.descent);
        return textHeight;
    }

    /**
     * dp转px
     * @param context context
     * @param dpValue dp
     * @return px
     */
    public static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}