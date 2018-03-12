package com.bill.util;

/**
 * Created by E on 2018/3/12.
 */
public class PrefHelper {

    public static void initPointNames(){
        PrefUtil.instance().setIntPref(Prefkey.ADD_POINT , 1);
    }

    public static boolean initedPointNames(){
        return 1 == PrefUtil.instance().getIntPref(Prefkey.ADD_POINT , 0);
    }


}
