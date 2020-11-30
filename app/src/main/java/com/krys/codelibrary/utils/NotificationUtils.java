package com.krys.codelibrary.utils;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.krys.codelibrary.receivers.NotificationDismissReceiver;
import com.krys.codelibrary.R;
import com.krys.codelibrary.activity.Dashboard;
import com.krys.codelibrary.services.DirectReplyIntent;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtils {

    @SuppressLint("StaticFieldLeak")
    private static NotificationCompat.Builder mBuilder;
    private static final int REQUEST_CODE = 12;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel setNotiChannel() {
        NotificationChannel channel = new NotificationChannel(ConstantStrings.NOTIFICATION_CHANNEL_ID,
                ConstantStrings.NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(ConstantStrings.NOTIFICATION_CONTENT_DESCRIPTION);
        channel.enableLights(true);
        return channel;
    }

    private static void getNotiManager(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (isOreo()) notificationManager.createNotificationChannel(setNotiChannel());
        notificationManager.notify(ConstantStrings.NOTIFICATION_ID, mBuilder.build());
    }

    public static void clearNotifications(Context context, int notificationId){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);
    }

    private static boolean isOreo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    private static PendingIntent createPendingIntent(Context context, int requestCode, int flags, Intent resultIntent){
        return PendingIntent.getActivity(context, requestCode, resultIntent, flags);
    }

    private static PendingIntent createPendingService(Context context, int requestCode, int flags, Intent resultIntent){
        return PendingIntent.getService(context, requestCode, resultIntent, flags);
    }

    private static PendingIntent createPendingBroadcast(Context context, int requestCode, int flags, Intent resultIntent){
        return PendingIntent.getBroadcast(context, requestCode, resultIntent, flags);
    }

    private static Intent getIntent(Context context, Class<?> mClass){
        return new Intent(context, mClass);
    }

    public static void showSimpleNotification(Context context, String title, String message) {
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setAutoCancel(true);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, Dashboard.class)));
        getNotiManager(context);
    }

    public static void showBigTextNotification(Context context, String title, String message) {
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentText(message);
        mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText(message)
                .setSummaryText(title));
        mBuilder.setAutoCancel(true);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, Dashboard.class)));
        getNotiManager(context);
    }

    public static void showBigPictureNotification(Context context, String title, String message, Bitmap largeIcon, Bitmap picture) {
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentText(message);
        mBuilder.setContentTitle(title);
        mBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .setSummaryText(message)
                .setBigContentTitle(title)
                .bigLargeIcon(CommonUtils.getCircularBitmap(largeIcon))
                .bigPicture(picture));
        mBuilder.setAutoCancel(true);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, Dashboard.class)));
        getNotiManager(context);
    }

    public static void showInboxNotification(Context context, String title, ArrayList<String> messages) {
        NotificationCompat.InboxStyle iStyle =  new NotificationCompat.InboxStyle();
        for (int i = 0; i < messages.size(); i++) {
            iStyle.addLine(messages.get(i));
        }
        iStyle.setSummaryText("+"+messages.size()+" more");
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(messages.get(messages.size()-1));
        mBuilder.setStyle(iStyle);
        mBuilder.setAutoCancel(true);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, Dashboard.class)));
        getNotiManager(context);
    }

    public static void showNotificationWithReply(Context context, String title, String message) {
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setAutoCancel(true);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        Intent directReplyIntent = getIntent(context, DirectReplyIntent.class);
        directReplyIntent.putExtra(ConstantStrings.KEY_NOTIFY_ID, 82);
        directReplyIntent.setAction(Long.toString(System.currentTimeMillis()));
        RemoteInput remoteInput = new RemoteInput.Builder(ConstantStrings.KEY_TEXT_REPLY)
                .setLabel("Message").build();
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                R.drawable.ic_baseline_send_24, "Reply", createPendingService(context, REQUEST_CODE, PendingIntent.FLAG_CANCEL_CURRENT, directReplyIntent))
                .addRemoteInput(remoteInput).build();
        mBuilder.addAction(action);
        getNotiManager(context);
    }

    public static void showHeadsUpNotification(Context context, String title, String message) {
        Intent buttonIntent = getIntent(context, NotificationDismissReceiver.class);
        buttonIntent.setAction(Long.toString(System.currentTimeMillis()));
        buttonIntent.putExtra(ConstantStrings.KEY_NOTIFY_ID, ConstantStrings.NOTIFICATION_ID);
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setAutoCancel(true);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        mBuilder.addAction(R.drawable.ic_baseline_call_24, "Answer",
                createPendingIntent(context, REQUEST_CODE , PendingIntent.FLAG_CANCEL_CURRENT, getIntent(context, Dashboard.class)));
        mBuilder.addAction(R.drawable.ic_baseline_call_end_24, "Cancel",
                createPendingBroadcast(context, REQUEST_CODE, PendingIntent.FLAG_CANCEL_CURRENT , buttonIntent));
        getNotiManager(context);
    }

    public static void showMessageNotification(Context context,String title, String groupTitle, Bitmap largeIcon) {
        Person user = new Person.Builder()
                .setIcon(IconCompat.createWithBitmap(CommonUtils.getCircularBitmap(CommonUtils.getIcon(context, R.drawable.dummy_user1))))
                .setName("Krys").build();

        Person reciever = new Person.Builder()
                .setIcon(IconCompat.createWithBitmap(CommonUtils.getCircularBitmap(CommonUtils.getIcon(context, R.drawable.dummy_user2))))
                .setName("Riya").build();

        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle(user);
        style.setConversationTitle(groupTitle);
        style.addMessage("Hey there!", System.currentTimeMillis(), user);
        style.addMessage("Hi , long time no see", System.currentTimeMillis(), reciever);
        style.addMessage("Yeah bit busy with some cool stuff", System.currentTimeMillis(), user);

        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setAutoCancel(true);
        mBuilder.setStyle(style);
        mBuilder.setLargeIcon(largeIcon);
        mBuilder.setContentTitle(title);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, Dashboard.class)));
        getNotiManager(context);
    }

    public static void showCustomNotification(Context context, String title, String message, Bitmap bitmap) {
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.custom_notification_layout);
        contentView.setTextViewText(R.id.txtTitle, title);
        contentView.setTextViewText(R.id.txtMassage, message);
        contentView.setViewVisibility(R.id.imgMsg, View.VISIBLE);
        contentView.setImageViewBitmap(R.id.imgMsg, bitmap);
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setCustomBigContentView(contentView);
        mBuilder.setCustomContentView(contentView);
        mBuilder.setContent(contentView);
        mBuilder.setAutoCancel(true);
        mBuilder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, Dashboard.class)));
        getNotiManager(context);
    }

    public static void showProgressNotification(Context context, String title, String message) {
        mBuilder = isOreo() ? new NotificationCompat.Builder(context, ConstantStrings.NOTIFICATION_CHANNEL_ID) : new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(message);
        mBuilder.setAutoCancel(true);
        mBuilder.setOngoing(true);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
        mBuilder.setColor(ContextCompat.getColor(context, R.color.blue_A400));
        mBuilder.setContentIntent(createPendingIntent(context, REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT, getIntent(context, Dashboard.class)));
        new Thread(new Runnable() {
            @Override
            public void run() {
                int incr;
                for (incr = 0; incr <= 100; incr+=5) {
                    mBuilder.setProgress(100, incr, false);
                    getNotiManager(context);
                    try {
                        Thread.sleep(1*1000);
                    } catch (InterruptedException e) {
                        Log.e("TAG", "sleep failure");
                    }
                }
                mBuilder.setContentText("Download completed")
                        .setProgress(0,0,false);
                getNotiManager(context);
            }
        }
        ).start();

    }

}
