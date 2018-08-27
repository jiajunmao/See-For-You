package com.app.CClient.Utlis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lclient.Main.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {
    private static Dialog showProgressdialog;
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
    public static boolean isMobileNO(String mobiles) {
        /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        */
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public static Dialog showDialog(Context context, String msg, final View.OnClickListener onClickListener){
        final Dialog dialog = new Dialog(context, R.style.NobackDialog);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_normal, null);
        TextView message = (TextView) view.findViewById(R.id.message);
        message.setText(msg);

        View.OnClickListener _onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null)
                    onClickListener.onClick(v);
                dialog.dismiss();
            }
        };

        view.findViewById(R.id.dialogOk).setOnClickListener(_onClickListener);
        view.findViewById(R.id.dialogCancel).setOnClickListener(_onClickListener);


        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        return dialog;
    }
    public static Dialog showProgress(Context context){
        if(context instanceof Activity){
            return showProgress(context,null);
        }
        return null;
    }
    public static Dialog showProgress(Context context, String supplementStr){
        showProgressdialog = new Dialog(context, R.style.NobackDialog);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_progress, null);
        TextView supplement = (TextView) view.findViewById(R.id.supplement);
        if(supplementStr != null){
            supplement.setText(supplementStr);
        }

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        if(context instanceof Activity){
            Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
            width = display.getWidth();
        }

        showProgressdialog.setContentView(view,new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        showProgressdialog.setCanceledOnTouchOutside(true);
        if (!showProgressdialog.isShowing() && context!=null) {
            showProgressdialog.show();
        }

        return showProgressdialog;
    }
}
