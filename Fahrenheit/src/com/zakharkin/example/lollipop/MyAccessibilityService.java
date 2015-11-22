package com.zakharkin.example.lollipop;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by andre on 22.11.2015.
 */
public class MyAccessibilityService extends AccessibilityService {
    LogsDataHelper dataHelper;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            final String packageName = String.valueOf(event.getPackageName());
            Log.i("MyAccessibilityService", "Access Service event :" + packageName);
            Log.i("MyAccessibilityService", "Event Text: " + event.getText());
            if(dataHelper != null) {
                dataHelper.logText("Access Service event :" + packageName);
                dataHelper.logText("Event Text: " + event.getText());
            }
        }
    }

    boolean isInit;

    @Override
    protected void onServiceConnected() {
        Log.i("MyAccessibilityService", "Trying to connect");
        if (isInit) {
            return;
        }
        Log.i("MyAccessibilityService", "Service Connected");
        dataHelper = new LogsDataHelper(getBaseContext());
        dataHelper.logText("Service connected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        Log.i("MyAccessibilityService", "setServiceInfo");
        setServiceInfo(info);
        isInit = true;
    }

    @Override
    public void onInterrupt() {
        dataHelper.logText("Service interrupted");
        isInit = false;
    }
}
