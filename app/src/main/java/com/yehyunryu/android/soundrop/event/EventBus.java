package com.yehyunryu.android.soundrop.event;

import com.squareup.otto.Bus;

/**
 * Code Source - https://github.com/JayaprakashR-Zealot/SnapchatDashboard/blob/master/app/src/main/java/com/truedreamz/demo/swipe/event/EventBus.java
 * Provides a singleton instance for the application event bus.
 */
public class EventBus {

    private static final Bus sBus = new Bus();

    public static Bus getInstance() {
        //returns bus instance
        return sBus;
    }

    private EventBus() {
        //nothing, EventBus is just a keeper for real Bus instance
    }
}
