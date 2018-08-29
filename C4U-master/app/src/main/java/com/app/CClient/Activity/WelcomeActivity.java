package com.app.CClient.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.app.CClient.fragmenttabhost.R;

public class WelcomeActivity extends Activity implements View.OnClickListener {
    private Button welcome_bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initview();
    }
    private void initview(){
        welcome_bt = (Button) findViewById(R.id.welcome_bt);
        welcome_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.welcome_bt:
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
