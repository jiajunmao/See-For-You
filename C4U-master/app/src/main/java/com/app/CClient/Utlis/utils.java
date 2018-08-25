package com.app.CClient.Utlis;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public   static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    public  static boolean validatesEqual(String str1, String str2){
        boolean isEqual=false;

        if(str1==null||str2==null){
            isEqual=false;
        }else if(str1.equals(str2)){
            isEqual=true;
        }else{
            isEqual=false;
        }

        return isEqual;
    }
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
