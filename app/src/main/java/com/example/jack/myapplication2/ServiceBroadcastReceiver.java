package com.example.jack.myapplication2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jack on 2017/4/13.
 * <p>
 * PC端命令
 * <p>
 * Runtime.getRuntime().exec(
 * "adb shell am broadcast -a NotifyServiceStart");
 */

public class ServiceBroadcastReceiver extends BroadcastReceiver {

    private static String START_ACTION = "NotifyServiceStart";
    private static String STOP_ACTION = "NotifyServiceStop";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        MainActivity activity = new MainActivity();
        if (START_ACTION.equalsIgnoreCase(action)) {
            activity.runTest(activity.getTestUrl());
        }
    }
}
