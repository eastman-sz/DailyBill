package com.bill.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bill.bill.DaiyBillDbHelper;
import com.bill.billbook.BillbookDbHelper;
import com.bill.consumption.type.BigTypeDbHelper;
import com.bill.consumption.type.SmallTypeDbHelper;
import com.bill.point.ConsumptionPointDbHelper;
/**
 * Created by E on 2017/4/20.
 */
public class IDbHelper extends SQLiteOpenHelper {

    /**
     * 更新历史
     */
    private static final int VERSION = 1; //2018-01-26
    private static final String DBNAME =  "dailybillMaindb";

    public IDbHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DaiyBillDbHelper.createTable(db);
        ConsumptionPointDbHelper.createTable(db);
        BillbookDbHelper.createTable(db);
        SmallTypeDbHelper.Companion.createTable(db);
        BigTypeDbHelper.Companion.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DaiyBillDbHelper.dropTable(db);
        ConsumptionPointDbHelper.dropTable(db);
        BillbookDbHelper.dropTable(db);

        SmallTypeDbHelper.Companion.dropTable(db);
        BigTypeDbHelper.Companion.dropTable(db);

        onCreate(db);
    }
}
