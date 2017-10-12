package com.yehyunryu.android.soundrop.event;

/**
 * Code Source - https://github.com/JayaprakashR-Zealot/SnapchatDashboard/blob/master/app/src/main/java/com/truedreamz/demo/swipe/event/PageChangedEvent.java
 * Called when the current selected page of the application navigation change.
 * ex) User swipes left
 */
public class PageChangeEvent {
    private boolean mHasVertical = true;

    public PageChangeEvent(boolean hasVertical) {
        //true if selected page has vertical pages, false otherwise
        mHasVertical = hasVertical;
    }

    public boolean hasVertical() {
        //return whether page has vertical pages
        return mHasVertical;
    }
}
