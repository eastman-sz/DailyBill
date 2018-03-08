package com.bill.db;

import android.database.sqlite.SQLiteDatabase;
import com.bill.application.IApplication;
/**
 * @author E
 */
public class ISqliteDataBase {

	private static SQLiteDatabase sqLiteDatabase = null;

	private final static Object object = new Object();
	
	public static SQLiteDatabase getSqLiteDatabase(){
		if (null == sqLiteDatabase) {
			synchronized (object.getClass()) {
				if (null == sqLiteDatabase) {
					sqLiteDatabase = new IDbHelper(IApplication.getContext()).getWritableDatabase();
				}
			}
		}
		return sqLiteDatabase;
	}

}
