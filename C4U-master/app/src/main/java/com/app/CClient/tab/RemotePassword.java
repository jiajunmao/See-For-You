package com.app.CClient.tab;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by jiapeng on 2017/9/15.
 */

public class RemotePassword {

    private static final int STATE_NOT_PASSWORD = 0;
    private static final int STATE_ERROR_PASSWORD = 1;
    private static final int STATE_RIGHT_PASSWORD = 2;


    private SharedPreferences sp;
    private String serial;
    public RemotePassword(Context context,String serial) {
//        this.mContext = context;
        this.serial = serial;
        sp = context.getSharedPreferences("",Context.MODE_PRIVATE);
    }

    public boolean checkPassword(String password){
        String pwd = sp.getString(prefix(""),null);
        if (pwd!=null){
            Log.e("checkPassword:","不为空:" +pwd);
            return pwd.equals(password);
        }
        Log.e("checkPassword:","为空");
        return false;
    }


    private final String prefix(String key){
        return serial+key;
    }
}
