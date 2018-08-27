package com.app.CClient.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.app.CClient.fragmenttabhost.R;
import com.app.CClient.view.MyClickListener;

public class VisuallyimpairedActivity extends Activity implements
        MyClickListener.MyClickCallBack{
    private LinearLayout vi_ll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visuallyimpaired);
    }
  private void initview(){
      vi_ll = (LinearLayout) findViewById(R.id.visuallyimpaired_ll);
      vi_ll.setOnTouchListener(new MyClickListener(this));
  }

    @Override
    public void oneClick() {
        Log.e("MyClickListener","单击");
    }

    @Override
    public void doubleClick() {
        Log.e("MyClickListener","双击");
    }
}
