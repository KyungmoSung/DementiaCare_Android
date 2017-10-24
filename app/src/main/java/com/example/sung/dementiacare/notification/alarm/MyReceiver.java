package com.example.sung.dementiacare.notification.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.sung.dementiacare.R;

/**
 * Created by minwoopark on 2017. 10. 23..
 */

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() { }

    private NotificationManager mManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder mBuilder = new Notification.Builder(context);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setTicker("hi");
        mBuilder.setContentTitle("hi1");
        mBuilder.setContentText("hi3");

        mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        //mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        nm.notify(111, mBuilder.build());

    }
}
