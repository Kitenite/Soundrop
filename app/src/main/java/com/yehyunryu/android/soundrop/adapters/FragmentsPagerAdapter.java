package com.yehyunryu.android.soundrop.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Code Source - https://github.com/JayaprakashR-Zealot/SnapchatDashboard/blob/master/app/src/main/java/com/truedreamz/demo/swipe/adapter/FragmentsClassesPagerAdapter.java
 * Adapter for {@link ViewPager} that will populated from the collection of Fragments classes. Objects of that classes
 * will be instantiated on demand and used as a pages views.
 */

public class FragmentsPagerAdapter extends FragmentPagerAdapter {

    private List<Class<? extends Fragment>> mPagesClasses;
    private Context mContext;

    public FragmentsPagerAdapter(FragmentManager fragmentManager, Context context, List<Class<? extends Fragment>> pages) {
        super(fragmentManager);
        mPagesClasses = pages;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return Fragment.instantiate(mContext, mPagesClasses.get(position).getName());
    }

    @Override
    public int getCount() {
        return mPagesClasses.size();
    }
}
