package com.app.CClient.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.CClient.fragmenttabhost.R;

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText user_et;
    private EditText pwd_et;
    private Button register_bt;
    private Button login_bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_et = (EditText) findViewById(R.id.login_user_et);
        pwd_et = (EditText) findViewById(R.id.login_password_et);
        register_bt = (Button) findViewById(R.id.register_bt);
        register_bt.setOnClickListener(this);
        login_bt = (Button) findViewById(R.id.login_bt);
        login_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_bt:

                break;
            case R.id.login_bt:

                break;
        }
    }
}
