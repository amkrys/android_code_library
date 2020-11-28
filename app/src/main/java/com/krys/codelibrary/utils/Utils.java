package com.krys.codelibrary.utils;

import android.content.Context;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

public class Utils {

    public static boolean isDarkMode(Context context){
        boolean mode = false;
        int nightModeFlags = context.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                mode = true;
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                mode = false;
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                mode = false;
                break;
        }
        return mode;
    }

    public static void disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static void enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

}


