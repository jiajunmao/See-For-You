package com.app.CClient.Utlis;


import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

public class ScreenInfo {
    public static int screen_w;
    public static int screen_h;
    public static float screen_d;

    public static void initData(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        } else {
            // TODO this is not correct, but we don'thumb_black really care pre-kitkat
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        }
        screen_w = metrics.widthPixels; // 得到屏幕宽度
        screen_h = metrics.heightPixels; // 得到屏幕高度
        screen_d = metrics.density;
        Log.e("requestWindowFeature", "initData:");
    }

    /**
     * 单位转换方法
     *
     * @param dp
     * @return
     */
    public static int Dp2Px(float dp) {
        return (int) (dp * screen_d + 0.5f);
    }

    public static int Px2Dp(float px) {
        return (int) (px / screen_d + 0.5f);
    }
}
