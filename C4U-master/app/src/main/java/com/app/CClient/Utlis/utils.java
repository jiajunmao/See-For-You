package com.app.CClient.Utlis;

import android.content.Context;
import android.widget.Toast;

public class utils {
    private static Toast mToast = null;
    public static void showToast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }

        mToast.show();
    }
}
