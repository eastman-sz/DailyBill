package com.bill.application;

import android.app.Application;
import android.content.Context;

import com.bill.umeng.UMConstant;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Created by E on 2018/3/8.
 */
public class IApplication extends Application {

    private static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        UMConfigure.init(context , UMConstant.AppKey , "10000" , UMConfigure.DEVICE_TYPE_PHONE , null);
        MobclickAgent.setScenarioType(this , MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    public static Context getContext(){
        return context;
    }
}
