package com.example.sung.dementiacare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.example.sung.dementiacare.notification.alarm.MyReceiver;

/**
 * Created by minwoopark on 2017. 10. 23..
 */

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent myIntent = new Intent(context, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0);

        long period = 1000 * 5;
        long after = 1000 * 5;
        long t = SystemClock.elapsedRealtime();
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, t + after, period, pendingIntent);
    }
}
