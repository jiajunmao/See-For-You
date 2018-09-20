package com.app.CClient.Utlis;

import android.content.Context;
import android.os.PowerManager;
/**
 * Created by Administrator on 2016/12/1.
 */
public class WakeLockUtil
{
    private static PowerManager.WakeLock mWakeLock;

    public static void acquire(Context context)
    {
        if (mWakeLock == null)
        {
            PowerManager pManager = ((PowerManager) (context.getSystemService(Context.POWER_SERVICE)));
            mWakeLock = pManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                    | PowerManager.ON_AFTER_RELEASE, "WakeLockUtil");
        }
        mWakeLock.acquire();

    };

    public static void release()
    {
        if (mWakeLock != null)
        {
            mWakeLock.release();
            mWakeLock = null;
        }

    }
}
