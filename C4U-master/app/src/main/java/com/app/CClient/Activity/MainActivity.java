package com.app.CClient.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.app.CClient.fragmenttabhost.R;
import com.app.CClient.fragmenttabhost.TrendFragment;
import com.app.CClient.fragmenttabhost.MineFragment;
import com.app.CClient.fragmenttabhost.ControlFragment;
import com.app.CClient.tab.BarEntity;
import com.app.CClient.tab.BottomTabBar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomTabBar.OnSelectListener{
    private TrendFragment  trendFragment;
    private ControlFragment  controlFragment;
    private MineFragment  mineFragment;
    private TextView titile_tv;
    private BottomTabBar tb ;
    private List<BarEntity> bars ;
    private FragmentManager manager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        initView();

    }

    private void initView() {
        titile_tv = (TextView) findViewById(R.id.titile_tv);
        manager = getSupportFragmentManager();
        tb = (BottomTabBar) findViewById(R.id.tb);
        bars = new ArrayList<>();
        bars.add(new BarEntity("trend", R.drawable.trend_selected, R.drawable.trend));
        bars.add(new BarEntity("control", R.drawable.control_selected, R.drawable.control));
        bars.add(new BarEntity("Mine", R.drawable.mine_selected, R.drawable.mine));
        tb.setManager(manager).setOnSelectListener(this).setBars(bars);
    }

    @Override
    public void onSelect(int position) {
        switch (position){
            case 0:
                if (trendFragment==null){
                    trendFragment = new TrendFragment();
                    titile_tv.setText("trend");
                    trendFragment.setContext(MainActivity.this);
                }
                tb.switchContent(trendFragment);
                break;
            case 1:
                if (controlFragment==null){
                    controlFragment = new ControlFragment();
                    titile_tv.setText("control");
                    controlFragment.setContext(MainActivity.this);
                }
                tb.switchContent(controlFragment);
                break;
            case 2:
                if (mineFragment==null){
                    mineFragment = new MineFragment();
                    titile_tv.setText("Mine");
                    mineFragment.setContext(MainActivity.this);
                }
                tb.switchContent(mineFragment);
                break;
            default:
                break;
        }

    }
}
