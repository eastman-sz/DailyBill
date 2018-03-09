package com.bill.util;

import android.text.TextUtils;

/**
 * Created by E on 2018/3/9.
 */
public class CommonUtil {

    public static String trimLastZero(String text){
        if (TextUtils.isEmpty(text)){
            return text;
        }
        int index = text.indexOf(".");
        if (-1 == index){
            return text;
        }
        if (text.endsWith("00")){
            text = text.substring(0 , text.length() - 2);
        }
        if (text.endsWith("0")){
            text = text.substring(0 , text.length() - 1);
        }
        if (text.endsWith(".")){
            text = text.substring(0 , text.length() - 1);
        }
        return text;
    }


}
