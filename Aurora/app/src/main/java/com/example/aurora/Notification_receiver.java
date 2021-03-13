package com.example.aurora;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager notificationManager_daily = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



        Intent repeating_intent = new Intent(context,Repeating_activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //PendingIntent pendingIntent_start_of_the_day = PendingIntent.getActivity(context,200,null,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,102,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder_start_of_the_day = new NotificationCompat.Builder(context, "lemubitNotify")
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Morning Notification")
                .setContentText("Have a great day")
//                .setSound(defaultSoundUri1)
                .setAutoCancel(true)
//                .setContentIntent(pendingIntent_start_of_the_day)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "lemubitNotify")
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Daily Notification")
                .setContentText("How was your day")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,)
//
//                ;

//        if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
//            notificationManager.notify(100,builder.build());
//            notificationManager.notify(200,builder_start_of_the_day.build());
////            Log.i("Notify", "Alarm"); //Optional, used for debuging.
//        }
        if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
            notificationManager.notify(100,builder.build());

//            Log.i("Notify", "Alarm"); //Optional, used for debuging.
        }

        if (intent.getAction().equals("MY_NOTIFICATION_MESSAGE_DAILY")) {
            notificationManager_daily.notify(200,builder_start_of_the_day.build());
//            Log.i("Notify", "Alarm"); //Optional, used for debuging.
        }

    }
}
