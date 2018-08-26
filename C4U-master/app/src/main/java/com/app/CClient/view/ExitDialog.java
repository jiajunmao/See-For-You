package com.app.CClient.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.app.CClient.fragmenttabhost.R;


public class ExitDialog extends Dialog {
    private Context mContext;
    private LinearLayout layoutCancel;
    private LinearLayout layoutCommit;
    public ExitDialog(Context context) {
        super(context, R.style.ExitDialog);
        mContext=context;
    }

    public ExitDialog(Context context, int theme) {
        super(context, theme);
        mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
        this.setCanceledOnTouchOutside(false);
        //设置为点击对话框之外的区域对话框不消失
        layoutCancel = (LinearLayout)findViewById(R.id.layout_item_dialog_cancel);
        layoutCommit = (LinearLayout)findViewById(R.id.layout_item_dialog_commit);
        //设置事件
        layoutCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        layoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitDialog.this.dismiss();
            }
        });

    }

}
