package com.app.CClient.fragmenttabhost;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.CClient.Utlis.utils;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class ControlFragment extends Fragment implements View.OnClickListener{
    private TextView call_tv;
    private View mRootView;
    private Context context;
    public void setContext(Context context){
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            Log.e("666","ControlFragment");
            mRootView = inflater.inflate(R.layout.report_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        initview(mRootView);
        if (parent != null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }
    private void initview(View view){
        call_tv = (TextView) view.findViewById(R.id.call_tv);
        call_tv.setOnClickListener(this);
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_tv:
                utils.showDialog(context, getString(R.string.dialog_msg), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
    }
}
