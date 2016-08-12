package com.chinosoft.p2pinvest.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.Preference;
import android.util.Log;

import com.chinosoft.p2pinvest.utils.PreferenceUtil;

/**
 * Created by Administrator on 2015/12/11.
 */
public class MyApplication extends Application {

     public static Context context = null;

    public static Handler handler = null;

    public static Thread mainThread = null;

    public static int mainThreadId = 0;

    public static PreferenceUtil  preferenceUtil = null;

    public static String username = null;
    public static String password = null;


    @Override
    public void onCreate() {
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
//      CrashHandler.getInstance().init(this);
        preferenceUtil = new PreferenceUtil(context,"user");
        username = preferenceUtil.getString("username");
        super.onCreate();
    }

    public static void saveUser(String username)
    {
        preferenceUtil.put("username",username);
        MyApplication.username = username;
    }

    public static void deleteUser(String username)
    {
        preferenceUtil.remove("username");
        MyApplication.username = null;
    }


}
