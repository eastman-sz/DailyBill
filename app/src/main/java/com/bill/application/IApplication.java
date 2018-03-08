package com.bill.application;

import android.app.Application;
import android.content.Context;
/**
 * Created by E on 2018/3/8.
 */
public class IApplication extends Application {

    private static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
