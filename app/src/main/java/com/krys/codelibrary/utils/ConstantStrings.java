package com.krys.codelibrary.utils;

import java.util.Random;

public class ConstantStrings {

    public static int NOTIFICATION_ID = new Random().nextInt(99-1)+1;
    public static String NOTIFICATION_CHANNEL_ID = "amkrys";
    public static String NOTIFICATION_CHANNEL_NAME = "CodeLibrary";
    public static String NOTIFICATION_CONTENT_DESCRIPTION = "Testing All Notification Types";

    public static String KEY_TEXT_REPLY = "key_text_reply";
    public static String KEY_NOTIFY_ID = "key_notify_id";

    public static String API_URL = "https://api.stackexchange.com/2.2/";
    public static String ROOM_API_URL = "https://api.themoviedb.org/";
    public static String BACK_DROP_BASE = "http://image.tmdb.org/t/p/w780";
    public static String POSTER_BASE = "http://image.tmdb.org/t/p/w185";
}
