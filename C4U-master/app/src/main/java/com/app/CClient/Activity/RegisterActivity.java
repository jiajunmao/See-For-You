package com.app.CClient.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.app.CClient.Utlis.EditTextChecker;
import com.app.CClient.Utlis.EditTextStyle;
import com.app.CClient.Utlis.HttpUtils;
import com.app.CClient.Utlis.utils;
import com.app.CClient.fragmenttabhost.R;
/*
*
* */

public class RegisterActivity extends Activity implements OnClickListener{
    private EditText register_user_et,register_password_et,register_confirmpassword_et,register_email_et,register_phone_et;
    private Button register_bt;
    private EditTextChecker pwdTextChecker;
    private String register_phone = "";
    private Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 11){
                utils.showToast(RegisterActivity.this,getText(R.string.http_data_error),0);
            }
            if(msg.what == 1){
                //Parameter error returns error
                //User name repeatedly returned no
                //registerSuccess ok

                String strResult = (String)msg.obj;
                String resultReal = /*AESHelper.decrypt(strResult, AppcalitaionContext.AES_PASSWORD)*/"";
                try{
                    if(resultReal.equals("error")){
                        utils.showToast(RegisterActivity.this,getText(R.string.http_parameters_error),0);
                        return;
                    }
                    if(resultReal.equals("no")){
                        utils.showToast(RegisterActivity.this,getText(R.string.Account_name_exists),0);
                        return;
                    }
                    if(resultReal.equals("ok")){
                        utils.showToast(RegisterActivity.this,getText(R.string.register_Success),0);
                        finish();
                    }
                }catch (Exception e){

                }

            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initview();

    }
    private void initview(){
        register_user_et = (EditText) findViewById(R.id.register_user_et);
        register_password_et = (EditText) findViewById(R.id.register_password_et);
        register_confirmpassword_et = (EditText) findViewById(R.id.register_confirmpassword_et);
        register_email_et = (EditText) findViewById(R.id.register_email_et);
        register_bt = (Button) findViewById(R.id.register_bt);
        register_phone_et = (EditText) findViewById(R.id.register_phone_et);
        register_bt.setOnClickListener(this);
        pwdTextChecker = new EditTextChecker(this);
        pwdTextChecker.addCheckTask(register_user_et,EditTextStyle.BOUND.getEntity(6,11),R.string.Please_enter_user);
        pwdTextChecker.addCheckTask(register_password_et, EditTextStyle.BOUND.getEntity(6,11),R.string.Please_enter_password);
        pwdTextChecker.addCheckTask(register_confirmpassword_et, EditTextStyle.BOUND.getEntity(6,11),R.string.Please_enter_password);
        pwdTextChecker.addCheckTask(register_password_et, EditTextStyle.EQUAL.getEntity(register_confirmpassword_et),R.string.password_different);
        pwdTextChecker.addCheckTask(register_email_et,EditTextStyle.EMAIL,R.string.mailbox_format);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.register_bt:
                String user = register_user_et.getText().toString().trim();
                String password = register_password_et.getText().toString().trim();
                String confirmpassword = register_confirmpassword_et.getText().toString().trim();
                String email = register_email_et.getText().toString().trim();
                boolean isemail1 = utils.isEmail(email);

                if(utils.isEmpty(user)){
                    utils.showToast(RegisterActivity.this,getText(R.string.Account_empty),0);
                    return;
                }
                if(utils.isEmpty(password)){
                    utils.showToast(RegisterActivity.this,getText(R.string.password_empty),0);
                    return;
                }
                if(utils.isEmpty(confirmpassword)){
                    utils.showToast(RegisterActivity.this,getText(R.string.password_empty),0);
                    return;
                }

                if (user.length()<6||user.length()>11){
                    utils.showToast(RegisterActivity.this,getText(R.string.Please_enter_user),0);
                    return;
                }
                if (password.length()<6||password.length()>11){
                    utils.showToast(RegisterActivity.this,getText(R.string.Please_enter_password),0);
                    return;
                }

                if (!utils.validatesEqual(password, confirmpassword)){
                    utils.showToast(RegisterActivity.this,getText(R.string.password_different),0);
                    return;
                }
                if (!isemail1){
                    utils.showToast(RegisterActivity.this,getText(R.string.mailbox_format),0);
                    return;
                }
           /*      if (!pwdTextChecker.check()){
                     Log.e("pwdTextChecker","1");
                     return;
                 }*/
                Log.e("pwdTextChecker","2");
                 finish();
                break;
        }
    }


    class GetRegisterMessage implements Runnable{
        @Override
        public void run() {
            Message msg = handle.obtainMessage();
 /*           String strUserName = edUserName.getText().toString().trim();
            String strPassWord = edPassWord.getText().toString().trim();
            String strRequest = strUserName+"|"+strPassWord;*/

 /*           String requestMessage = AESHelper.encrypt(strRequest, AppcalitaionContext.AES_PASSWORD);
            String buyURL = AppcalitaionContext.REGISTER_URL+requestMessage;*/
            String strResultAes = HttpUtils.doGet(/*buyURL*/"");
            if(utils.isEmpty(strResultAes)){
                msg.what = 11;
            }
            else{
                msg.what = 1;
                msg.obj = strResultAes;
            }
            handle.sendMessage(msg);
        }
    }
}
