package com.yehyunryu.android.soundrop.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yehyunryu.android.soundrop.R;
import com.yehyunryu.android.soundrop.adapters.MainPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_viewpager) ViewPager mViewPager;

    private MainPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind views using butterknife
        ButterKnife.bind(this);

        //plant Timber debug tree for logging
        Timber.plant(new Timber.DebugTree());

        //initialize pager adapter and set it to viewpager
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        //set current item at 1 so that it starts at home fragment
        mViewPager.setCurrentItem(1);
    }
}
