package com.krys.codelibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.krys.codelibrary.utils.ConstantStrings;
import com.krys.codelibrary.utils.NotificationUtils;

public class NotificationDismissReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra(ConstantStrings.KEY_NOTIFY_ID, 0);
        NotificationUtils.clearNotifications(context, notificationId);
    }
}