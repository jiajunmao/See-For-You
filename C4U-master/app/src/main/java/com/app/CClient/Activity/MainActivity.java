package com.app.CClient.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.app.CClient.fragmenttabhost.TrendFragment;
import com.app.CClient.fragmenttabhost.MineFragment;
import com.app.CClient.fragmenttabhost.R;
import com.app.CClient.fragmenttabhost.ControlFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private Class mClass[] = {TrendFragment.class,ControlFragment.class,MineFragment.class};
    private Fragment mFragment[];

    private TrendFragment  trendFragment;
    private ControlFragment  controlFragment;
    private MineFragment  mineFragment;

    private String mTitles[] = {"Trend","Control","Mine"};
    private TextView titile_tv;
    private int mImages[] = {
            R.drawable.tab_trend,
            R.drawable.tab_control,
            R.drawable.tab_mine,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {

        initView();

        initEvent();
    }

    private void initView() {
        trendFragment = new TrendFragment();
        controlFragment = new ControlFragment();
        mineFragment = new MineFragment();
        mFragment = new Fragment[mTitles.length];
        mFragment[0] = trendFragment;
        mFragment[1] = controlFragment;
        mFragment[2] = mineFragment;
        trendFragment.setContext(MainActivity.this);
        controlFragment.setContext(MainActivity.this);
        mineFragment.setContext(MainActivity.this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        titile_tv = (TextView) findViewById(R.id.titile_tv);
        mFragmentList = new ArrayList<Fragment>();

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0;i < mFragment.length;i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec,mClass[i],null);
            mFragmentList.add(mFragment[i]);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
            Log.e("mFragmentList","for" + i);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.e("mFragmentList","getItem" + position);
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                Log.e("mFragmentList","size" +mFragmentList.size());
                return mFragmentList.size();
            }
        });
    }

    private View getTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);

        image.setImageResource(mImages[index]);
        title.setText(mTitles[index]);
        return view;
    }

    private void initEvent() {

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mViewPager.setCurrentItem(mTabHost.getCurrentTab());
                Log.e("mFragmentList","getCurrentTab" +mTabHost.getCurrentTab());
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
                titile_tv.setText(mTitles[position]);
                Log.e("mFragmentList","onPageSelected" +position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
