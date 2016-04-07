package com.apps.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kira on 2016/4/7.
 */
public class SharedPreferenceUtils {
    private static final String SP_NAME = "theme";
    private static final String KEY_IS_NIGHT = "isNight";

    public static SharedPreferences getSharedPreferences(Context context){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sp;
    }

    public static void save(Context context,boolean isNight){
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_IS_NIGHT,isNight);
        editor.commit();
    }

    public static boolean get(Context context){
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getBoolean(KEY_IS_NIGHT,false);
    }

    public static void clear(Context context){
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
