package com.yehyunryu.android.soundrop.viewpagers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Yehyun Ryu on 10/11/2017.
 */

public class SmartViewPager extends ViewPager {
    private GestureDetector mGestureDetector;
    private boolean mIsLockOnHorizontalAxis = false;

    public SmartViewPager(Context context,AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new XScrollDetector());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!mIsLockOnHorizontalAxis) mIsLockOnHorizontalAxis = mGestureDetector.onTouchEvent(ev);

        if(ev.getAction() == MotionEvent.ACTION_UP) mIsLockOnHorizontalAxis = false;

        getParent().requestDisallowInterceptTouchEvent(mIsLockOnHorizontalAxis);
        return super.onTouchEvent(ev);
    }

    private class XScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX) > Math.abs(distanceY);
        }
    }
}
