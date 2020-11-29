package com.krys.codelibrary.utils;

import java.util.Random;

public class ConstantStrings {

    public static int NOTIFICATION_ID = new Random().nextInt(99-1)+1;
    public static String NOTIFICATION_CHANNEL_ID = "amkrys";
    public static String NOTIFICATION_CHANNEL_NAME = "CodeLibrary";
    public static String NOTIFICATION_CONTENT_DESCRIPTION = "Testing All Notification Types";

    public static String KEY_TEXT_REPLY = "key_text_reply";
    public static String KEY_NOTIFY_ID = "key_notify_id";
}
