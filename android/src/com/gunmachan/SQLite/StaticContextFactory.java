package com.gunmachan.SQLite;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * StaticContextFactory class used to create factory methods that
 * returns the application context.
 *
 * @author pdunlavey
 * @version 1.0
 * @date 10-22-18
 */
@SuppressLint("Registered")
public class StaticContextFactory extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        StaticContextFactory.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return StaticContextFactory.context;
    }
}
