package com.app.CClient.fragmenttabhost;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class TrendFragment extends Fragment {

    private View mRootView;
    private Context context;
    private FrameLayout home_fl;
    public void setContext(Context context){
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            Log.e("666","TrendFragment");
            mRootView = inflater.inflate(R.layout.home_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        initview(mRootView);
        if (parent != null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }
  private void initview(View view){
      home_fl = (FrameLayout) view.findViewById(R.id.home_fl);
      home_fl.setVisibility(View.VISIBLE);
  }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (home_fl!=null){
            home_fl.setVisibility(View.GONE);
        }

    }
}
