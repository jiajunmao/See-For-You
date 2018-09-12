package com.app.CClient.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import com.app.CClient.Utlis.utils;
import com.app.CClient.fragmenttabhost.R;



public class BaseActivity extends AppCompatActivity {

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        actionBar = new ActionBar(this);

    }

    public void setActionBarTitle(String title){
        actionBar.setActionBarTitle(title);
    }

    public void setRightBtnBg(int resId,View.OnClickListener nextBtnClickListener){
        actionBar.setRightBtnBg(resId,nextBtnClickListener);
    }
    public View setRightBtn(int resId,View.OnClickListener nextBtnClickListener){
        return actionBar.setRightBtn(resId,nextBtnClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void startActivityByAnim(Class<?> _class){
        Intent intent = new Intent(this,_class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_animation_entr_in,R.anim.slide_animation_entr_out);
    }

    public void startActivityByAnim(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_animation_entr_in,R.anim.slide_animation_entr_out);
    }

    public void startActivityForResultByAnim(Class<?> _class,int requestCode){
        Intent intent = new Intent(this,_class);
        startActivityForResult(intent,requestCode);
        overridePendingTransition(R.anim.slide_animation_entr_in,R.anim.slide_animation_entr_out);
    }

    public void startActivityForResultByAnim(Intent intent,int requestCode){
        startActivityForResult(intent,requestCode);
        overridePendingTransition(R.anim.slide_animation_entr_in,R.anim.slide_animation_entr_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_animation_finish_in,R.anim.slide_animation_finish_out);
    }

    public final void showToast(final String title){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                utils.showToast(BaseActivity.this,title,0);
               /* Toast.makeText(BaseActivity.this,title,Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}
