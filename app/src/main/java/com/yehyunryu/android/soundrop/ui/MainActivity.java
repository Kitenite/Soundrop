package com.yehyunryu.android.soundrop.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yehyunryu.android.soundrop.BuildConfig;
import com.yehyunryu.android.soundrop.R;
import com.yehyunryu.android.soundrop.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_viewpager) ViewPager mViewPager;

    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //plant debug logging using timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        //bind views using butterknife
        ButterKnife.bind(this);

        //initialize and attach view pager adapter
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        //set current item to home fragment initially
        mViewPager.setCurrentItem(1);
    }

}