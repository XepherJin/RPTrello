package com.retropoktan.rptrello.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * An easy way to handle and calculate sth about displayMetrics.
 *
 * @author RetroPoktan
 */
public class DisplayUtil {

    private DisplayUtil() {

    }

    /**
     * get current displayMetrics
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getCurrentDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * get the density of the screen
     *
     * @param context
     * @return the density
     */
    public static float getDensity(Context context) {
        return getCurrentDisplayMetrics(context).density;
    }

    /**
     * px change to dp
     *
     * @param context
     * @param pxValue
     * @return dpValue
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = getDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp chage to px
     *
     * @param context
     * @param dpValue
     * @return pxValue
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = getDensity(context);
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp chage to px
     *
     * @param context
     * @param spValue
     * @return pxValue
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = getScaledDensity(context);
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px chage to sp
     *
     * @param context
     * @param pxValue
     * @return sp
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = getScaledDensity(context);
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * get the densityDpi of the screen
     *
     * @param context
     * @return the densityDpi
     */
    public static int getDensityDpi(Context context) {
        return getCurrentDisplayMetrics(context).densityDpi;
    }

    /**
     * get the height of the screen in px
     *
     * @param context
     * @return the height in px
     */
    public static int getScreenHeightInPx(Context context) {
        return getCurrentDisplayMetrics(context).heightPixels;
    }

    /**
     * get the width of the screen in px
     *
     * @param context
     * @return the width in px
     */
    public static int getScreenWidthInPx(Context context) {
        return getCurrentDisplayMetrics(context).widthPixels;
    }

    /**
     * get the scaledDensity of the screen
     *
     * @param context
     * @return the scaledDensity
     */
    public static float getScaledDensity(Context context) {
        return getCurrentDisplayMetrics(context).scaledDensity;
    }

    /**
     * get the xDpi of the screen
     *
     * @param context
     * @return the xDpi
     */
    public static float getXDpi(Context context) {
        return getCurrentDisplayMetrics(context).xdpi;
    }

    /**
     * get the yDpi of the screen
     *
     * @param context
     * @return the yDpi
     */
    public static float getYDpi(Context context) {
        return getCurrentDisplayMetrics(context).ydpi;
    }

    /**
     * get the height of the status bar in px.
     *
     * @param context
     * @return the height in px
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
