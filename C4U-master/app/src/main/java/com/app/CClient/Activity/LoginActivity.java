package com.app.CClient.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.app.CClient.Utlis.EditTextChecker;
import com.app.CClient.Utlis.EditTextStyle;
import com.app.CClient.fragmenttabhost.R;
import com.app.CClient.view.RippleLinearLayout;

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText user_et;
    private EditText pwd_et;
    private RippleLinearLayout register_bt;
    private Button login_bt;
    private EditTextChecker pwdTextChecker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        
    }
    private void initview(){
        pwdTextChecker = new EditTextChecker(this);
        user_et = (EditText) findViewById(R.id.login_user_et);
        pwd_et = (EditText) findViewById(R.id.login_password_et);
        register_bt = (RippleLinearLayout) findViewById(R.id.register_bt);
        register_bt.setOnClickListener(this);
        login_bt = (Button) findViewById(R.id.login_bt);
        login_bt.setOnClickListener(this);
        pwdTextChecker.addCheckTask(pwd_et, EditTextStyle.PASSWORD,R.string.password_empty);
        pwdTextChecker.setEmptyHintResId(R.string.password_empty);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_bt:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_bt:
                String user = user_et.getText().toString().trim();
            /*    if(utils.isEmpty(user)){
                    utils.showToast(LoginActivity.this,getText(R.string.Account_empty),0);
                    return;
                }

                String pwd = pwd_et.getText().toString().trim();
                if(!pwdTextChecker.check()) {
                    return;
                }*/
                Intent intentmai = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intentmai);
                break;
        }
    }
}
