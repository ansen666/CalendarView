package com.haibin.calendarviewproject.util;

import android.content.res.Resources;

/**
 * @author Ansen
 * @create time 2022/7/7
 */
public class DisplayHelper {
    public static int getWidthPixels() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}