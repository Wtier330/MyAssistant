package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.myapplication.constants.SPfconstants.SPF_MAIN_NAME;

public class MainSpfUtil {

    public static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_MAIN_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_MAIN_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");

    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_MAIN_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_MAIN_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);

    }

    public static int getIntWithDefault(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPF_MAIN_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defValue);

    }
}
