package com.yehyunryu.android.soundrop.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Code Source: https://github.com/JayaprakashR-Zealot/SnapchatDashboard/blob/master/app/src/main/java/com/truedreamz/demo/swipe/view/SmartViewPager.java
 * Custom {@link ViewPager} implementation to resolve scroll gesture directions more accurate than a regular
 * {@link ViewPager} component. This will make it perfectly usable into a scroll container such as {@link ScrollView},
 * {@link ListView}, etc.
 * Default ViewPager becomes hardly usable when it's nested into a scroll container. Such container will intercept any
 * touch event with minimal vertical shift from the child ViewPager. So switch the page by scroll gesture with a regular
 * {@link ViewPager} nested into a scroll container, user will need to move his finger horizontally without vertical
 * shift. Which is obviously quite irritating. {@link SmartViewPager} has a much much better behavior at resolving
 * scrolling directions.
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
        // decide if horizontal axis is locked already or we need to check the scrolling direction
        if(!mIsLockOnHorizontalAxis) mIsLockOnHorizontalAxis = mGestureDetector.onTouchEvent(ev);

        if(ev.getAction() == MotionEvent.ACTION_UP) mIsLockOnHorizontalAxis = false;

        getParent().requestDisallowInterceptTouchEvent(mIsLockOnHorizontalAxis);
        return super.onTouchEvent(ev);
    }

    //true - if we're scrolling in X direction, false - in Y direction
    private class XScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX) > Math.abs(distanceY);
        }
    }
}
