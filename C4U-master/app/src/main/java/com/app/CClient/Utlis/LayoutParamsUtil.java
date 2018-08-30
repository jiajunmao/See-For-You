package com.app.CClient.Utlis;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/12/1.
 */
public class LayoutParamsUtil
{
    public static ViewGroup.LayoutParams setSDLWindowSize(Context context,FrameLayout frameLayout)
    {
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        layoutParams.width = dm.widthPixels*2/3;
        layoutParams.height= dm.heightPixels*2/3*4/5;
        return layoutParams;
    }
}
