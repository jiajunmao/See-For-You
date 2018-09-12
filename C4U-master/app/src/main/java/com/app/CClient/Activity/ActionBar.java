package com.app.CClient.Activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.CClient.fragmenttabhost.R;


/**
 * Created by jiapeng on 2017/9/7.
 */

public class ActionBar {
    private TextView titleView;
    public Button nextBtn;
    private ImageView nextImgBtn;
    private Activity mActivity;

    private View.OnClickListener nextBtnClickListener;

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.backBtn:
                    mActivity.finish();
                    break;
            }
        }
    };


    public ActionBar(Activity activity) {

        mActivity = activity;
        Drawable colorDrawable = new ColorDrawable(0x00000000);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.action_bar, null);

        android.app.ActionBar actionbar = activity.getActionBar();
        if(actionbar != null) {
            actionbar.setBackgroundDrawable(colorDrawable);
            android.app.ActionBar.LayoutParams lp = new android.app.ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
            actionbar.setCustomView(v, lp);
            actionbar.setDisplayOptions(android.app.ActionBar.DISPLAY_SHOW_CUSTOM);

            titleView = (TextView) v.findViewById(R.id.titleView);
            v.findViewById(R.id.backBtn).setOnClickListener(clickListener);

        }
    }

    public void setActionBarTitle(String title){
        titleView.setText(title);
    }

    public View setRightBtn(int resId,View.OnClickListener nextBtnClickListener){
        if(nextBtn != null) {
            nextBtn.setText(mActivity.getString(resId));
            nextBtn.setVisibility(View.VISIBLE);
            this.nextBtnClickListener = nextBtnClickListener;
        }
        return nextBtn;
    }

    public void setRightBtnBg(int resId,View.OnClickListener nextBtnClickListener){
        if(nextImgBtn != null) {
            nextImgBtn.setImageResource(resId);
            nextImgBtn.setVisibility(View.VISIBLE);

            this.nextBtnClickListener = nextBtnClickListener;
        }
    }
}
