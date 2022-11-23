package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpfUtil {
    public static String SPF_NAME = "notespf";

    public static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");

    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);

    }

    public static int getIntWithDefault(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);

    }


}
