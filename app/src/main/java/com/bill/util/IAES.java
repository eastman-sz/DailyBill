package com.bill.util;

import com.utils.lib.ss.common.BaseAES;

/**
 * Created by E on 2017/4/14.
 */
public class IAES extends BaseAES {

    private static IAES iaes = null;

    public static IAES getInstance(){
        if (null == iaes) {
            iaes = new IAES();
        }
        return iaes;
    }

    @Override
    public String getAESKey() {
        return "0558ee57649be647d52f2549f8ddedfa";
    }
}
