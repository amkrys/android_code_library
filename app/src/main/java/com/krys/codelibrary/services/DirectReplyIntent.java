package com.krys.codelibrary.services;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.RemoteInput;

import com.krys.codelibrary.utils.ConstantStrings;
import com.krys.codelibrary.utils.NotificationUtils;

public class DirectReplyIntent extends IntentService {

    public DirectReplyIntent() {
        super(DirectReplyIntent.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        CharSequence directReply = getMessageText(intent);
        if (directReply != null) {
            NotificationUtils.showSimpleNotification(getApplicationContext(),
                    "Reply",
                    "Received: " + directReply);
        }
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(ConstantStrings.KEY_TEXT_REPLY);
        }
        return null;
    }
}