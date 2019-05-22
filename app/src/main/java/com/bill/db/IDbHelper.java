package com.bill.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bill.bill.DailyBillDbHelper;
import com.bill.billbook.BillBookDbHelper;
import com.bill.consumption.martket.MarketDbHelper;
import com.bill.consumption.nature.NatureInfoDbHelper;
import com.bill.consumption.payment.PaymentDbHelper;
import com.bill.consumption.type.BigTypeDbHelper;
import com.bill.consumption.type.SmallTypeDbHelper;
/**
 * Created by E on 2017/4/20.
 */
public class IDbHelper extends SQLiteOpenHelper {
    /**
     * 更新历史
     */
    private static final int VERSION = 2; //2018-01-26
    private static final String DBNAME =  "dailybillMaindb";

    public IDbHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DailyBillDbHelper.Companion.createTable(db);
        MarketDbHelper.Companion.createTable(db);
        BillBookDbHelper.Companion.createTable(db);
        SmallTypeDbHelper.Companion.createTable(db);
        BigTypeDbHelper.Companion.createTable(db);
        NatureInfoDbHelper.Companion.createTable(db);
        PaymentDbHelper.Companion.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DailyBillDbHelper.Companion.dropTable(db);
        MarketDbHelper.Companion.dropTable(db);
        BillBookDbHelper.Companion.dropTable(db);

        SmallTypeDbHelper.Companion.dropTable(db);
        BigTypeDbHelper.Companion.dropTable(db);

        NatureInfoDbHelper.Companion.dropTable(db);
        PaymentDbHelper.Companion.dropTable(db);

        onCreate(db);
    }
}
