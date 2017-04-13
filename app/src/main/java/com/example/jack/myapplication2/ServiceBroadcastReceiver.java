package com.example.jack.myapplication2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jack on 2017/4/13.
 * <p>
 * PC端命令
 * <p>
 * Runtime.getRuntime().exec(
 * "adb shell am broadcast -a NotifyServiceStart");
 */

public class ServiceBroadcastReceiver extends BroadcastReceiver {

    private static String START_ACTION = "ServiceBroadcastReceiver";
    private static String STOP_ACTION = "ServiceBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("TAGH5-BroadcastReceiver", action);
    }
}
