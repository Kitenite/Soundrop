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

public class CenterHorizontalFragment extends Fragment {
    public ViewPager mHorizontalPager;
    private int mCentralPageIndex = 0;
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            EventBus.getInstance().post(new PageChangeEvent(mCentralPageIndex == position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public CenterHorizontalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_center_horizontal, container, false);
        findViews(fragmentView);
        return fragmentView;
    }

    private void findViews(View fragmentView) {
        mHorizontalPager = (ViewPager) fragmentView.findViewById(R.id.fragment_central_horizontal_pager);
        initViews();
    }

    private void initViews() {
        populateHorizontalPager();
        mHorizontalPager.setCurrentItem(mCentralPageIndex);
        mHorizontalPager.addOnPageChangeListener(mPageChangeListener);
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
