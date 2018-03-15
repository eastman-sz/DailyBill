package com.bill.util;

import android.content.Intent;
import com.bill.application.IApplication;
/**
 * Created by E on 2018/3/15.
 */
public class BroadcastUtil {

    public static void sendBroadCast(String action){
        IApplication.getContext().sendBroadcast(new Intent(action));
    }

}
