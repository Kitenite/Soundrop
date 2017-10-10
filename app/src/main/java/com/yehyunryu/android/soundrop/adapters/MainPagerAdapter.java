package com.yehyunryu.android.soundrop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yehyunryu.android.soundrop.ui.DiscoverFragment;
import com.yehyunryu.android.soundrop.ui.HomeFragment;
import com.yehyunryu.android.soundrop.ui.StoryFragment;

/**
 * Created by Yehyun Ryu on 10/7/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new StoryFragment();
            case 1:
                return new HomeFragment();
            case 2:
                return new DiscoverFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
