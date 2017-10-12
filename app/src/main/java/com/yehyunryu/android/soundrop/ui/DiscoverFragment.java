package com.yehyunryu.android.soundrop.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yehyunryu.android.soundrop.R;

import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment {

    public DiscoverFragment() {
        //required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }
}
