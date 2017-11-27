package com.beaconyx.career.Push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.beaconyx.career.Activity.LoadActivity;
import com.beaconyx.career.Activity.MainActivity;
import com.beaconyx.career.Constant.IntentKeyConstant;
import com.beaconyx.career.R;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by pdg on 2017-11-21.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private final String CLASSNAME = getClass().getSimpleName();


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

    }

    @Override
    public void handleIntent(Intent intent) {

        PowerManager powerManager = (PowerManager) getApplication().getSystemService(Context.POWER_SERVICE);

        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "");

        wakeLock.acquire();
        String title = intent.getStringExtra("title");
        String msg = intent.getStringExtra("msg");

        changeAppBedgeCount(0);
        sendNotification(title, msg);

    }

    private void changeAppBedgeCount(int badgeCount){
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");

        intent.putExtra("badge_count", badgeCount);

        intent.putExtra("badge_count_package_name", getPackageName());

        intent.putExtra("badge_count_class_name", getLauncherClassName());

        sendBroadcast(intent);
    }

    private String getLauncherClassName(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager packageManager = getApplicationContext().getPackageManager();

        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);

        for(ResolveInfo info : resolveInfos){
            String packageName = info.activityInfo.applicationInfo.packageName;

            if(packageName.equalsIgnoreCase(getPackageName())){
                return info.activityInfo.name;
            }
        }

        return null;
    }

    private void sendNotification(String title, String msg) {
        Intent intent = new Intent(this, LoadActivity.class);
        intent.putExtra(IntentKeyConstant.AppPushIntentKey.PUSH_DATA_KEY, msg);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (title == null) {
            title = "Career";
        }

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.noti_icon))
                .setSmallIcon(R.drawable.noti_icon)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg)
                .setVibrate(new long[]{100, 100, 100, 100})
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        /**
         * 푸쉬 알림 보이기
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notifiBuilder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notifiBuilder.build());

    }

}
