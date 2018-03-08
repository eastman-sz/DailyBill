package com.bill.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by E on 2017/4/20.
 */
public class IDbHelper extends SQLiteOpenHelper {

    /**
     * 更新历史
     */
    private static final int VERSION = 17; //2018-01-26
    private static final String DBNAME =  "dailybillMaindb";

    public IDbHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
