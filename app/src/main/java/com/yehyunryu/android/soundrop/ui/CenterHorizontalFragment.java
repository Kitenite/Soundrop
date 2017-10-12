package com.yehyunryu.android.soundrop.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yehyunryu.android.soundrop.R;
import com.yehyunryu.android.soundrop.adapters.FragmentsPagerAdapter;
import com.yehyunryu.android.soundrop.event.EventBus;
import com.yehyunryu.android.soundrop.event.PageChangeEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Code Source: https://github.com/JayaprakashR-Zealot/SnapchatDashboard/blob/master/app/src/main/java/com/truedreamz/demo/swipe/fragment/CentralCompositeFragment.java
 */

/**
 * Fragment to manage the horizontal pages (left, central, right) of the 5 pages application navigation (top, center,
 * bottom, left, right).
 */
public class CenterHorizontalFragment extends Fragment {
    @BindView(R.id.fragment_central_horizontal_pager) ViewPager mHorizontalPager;

    private int mCentralPageIndex = 0;
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //nothing
        }

        @Override
        public void onPageSelected(int position) {
            EventBus.getInstance().post(new PageChangeEvent(mCentralPageIndex == position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //nothing
        }
    };

    public CenterHorizontalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_center_horizontal, container, false);
        ButterKnife.bind(this, fragmentView);
        populateHorizontalPager();
        mHorizontalPager.setCurrentItem(mCentralPageIndex);
        mHorizontalPager.addOnPageChangeListener(mPageChangeListener);
        return fragmentView;
    }

    private void populateHorizontalPager() {
        ArrayList<Class<? extends Fragment>> pages = new ArrayList<>();
        pages.add(LibraryFragment.class);
        pages.add(HomeFragment.class);
        pages.add(DiscoverFragment.class);
        mCentralPageIndex = pages.indexOf(HomeFragment.class);
        mHorizontalPager.setAdapter(new FragmentsPagerAdapter(getChildFragmentManager(), getActivity(), pages));
    }
}
