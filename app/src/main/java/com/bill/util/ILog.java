package com.bill.util;

import android.util.Log;

/**
 * Log类封装。
 * @author E
 */
public class ILog {

	private static final boolean DEBUG = true;
	private static final String IWY_TAG = "ilog";

	public static void e(String tag, String msg){
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}
	
	public static void e(String msg){
		if (DEBUG) {
			int msgLength = msg.length();
			if (msgLength < 1500){
                Log.e(IWY_TAG, msg);

                return;
            }


            int i = 0;
	        while (msgLength > 1500){
	            int start = i*1500;
	            int end = start + 1500;
                String subMsg = msg.substring(start , end);

                Log.e(IWY_TAG, subMsg);

                i ++;
                msgLength = msgLength - 1500;

                if (msgLength > 0 && msgLength <= 1500){
                    Log.e(IWY_TAG, msg.substring(end));
                }
            }

		}
	}

	public static void i(String tag, String msg){
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}
	
	public static void i(String msg){
		if (DEBUG) {
			Log.i(IWY_TAG, msg);
		}
	}
	
	public static void d(String tag, String msg){
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}
	
	public static void d(String msg){
		if (DEBUG) {
			Log.d(IWY_TAG, msg);
		}
	}
	
	public static void w(String tag, String msg){
		if (DEBUG) {
			Log.w(tag, msg);
		}
	}
	
	public static void w(String msg){
		if (DEBUG) {
			Log.w(IWY_TAG, msg);
		}
	}
	
	public static void v(String tag, String msg){
		if (DEBUG) {
			Log.v(tag, msg);
		}
	}
	
	public static void v(String msg){
		if (DEBUG) {
			Log.v(IWY_TAG, msg);
		}
	}

}
