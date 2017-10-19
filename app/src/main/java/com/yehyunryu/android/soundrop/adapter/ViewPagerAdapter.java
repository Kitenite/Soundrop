package com.yehyunryu.android.soundrop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yehyunryu.android.soundrop.ui.DiscoverFragment;
import com.yehyunryu.android.soundrop.ui.HomeFragment;
import com.yehyunryu.android.soundrop.ui.LibraryFragment;

/**
 * Created by Yehyun Ryu on 10/18/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                //leftmost fragment
                return new LibraryFragment();
            case 1:
                //center fragment
                return new HomeFragment();
            case 2:
                //rightmost fragment
                return new DiscoverFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        //return number of fragments that will populate main activity
        return 3;
    }
}
