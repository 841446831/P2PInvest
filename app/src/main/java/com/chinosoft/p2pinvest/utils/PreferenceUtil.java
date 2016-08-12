package com.chinosoft.p2pinvest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cai on 2016/7/20.
 */
public class PreferenceUtil {

    public SharedPreferences preferences;

    public PreferenceUtil(Context context, String name) {
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void put(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void put(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void put(String key, String value)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public void put(String key, Boolean value)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
        editor.commit();

    }
    public void put(String key, Float value)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key,value);
        editor.commit();
    }


    public int getInt(String key)
    {
        return preferences.getInt(key,0);
    }

    public Float getFloat(String key)
    {
        return preferences.getFloat(key,0);
    }
    public Boolean getLong(String key)
    {
        return preferences.getBoolean(key,true);
    }
    public String getString(String key)
    {
        return preferences.getString(key,null);
    }
}
