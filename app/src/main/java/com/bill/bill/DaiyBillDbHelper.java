package com.bill.bill;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bill.db.CursorHelper;
import com.bill.db.DbTableHelper;
import com.bill.db.ISqliteDataBase;
import java.util.ArrayList;
/**
 * Created by E on 2018/3/8.
 */
public class DaiyBillDbHelper {

    public static void save(long bid , long billtime ,long ctime ,String remarks ,int industryId ,int marketId){
        ContentValues values = new ContentValues();
        values.put("bid" , bid);
        values.put("billtime" , billtime);
        values.put("ctime" , ctime);
        values.put("remarks" , remarks);
        values.put("industryId" , industryId);
        values.put("marketId" , marketId);

        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        int count = db.update(DBNAME , values , "bid = ? " , new String[]{String.valueOf(bid)});
        if (count < 1){
            db.insert(DBNAME , null , values);
        }
    }

    public static ArrayList<DailyBill> getAllDailyBills(){
        ArrayList<DailyBill> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , null , null , null , null , null);
            while (null != cursor && cursor.moveToNext()){
                DailyBill dailyBill = fromCursor(cursor);

                list.add(dailyBill);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != cursor){
                cursor.close();
            }
        }
        return list;
    }

    public static ArrayList<DailyBill> getDailyBills(long startTime , long endTime){
        ArrayList<DailyBill> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , "billtime > ? and billtime < ? " ,
                    new String[]{String.valueOf(startTime) , String.valueOf(endTime)} , null , null , null);
            while (null != cursor && cursor.moveToNext()){
                DailyBill dailyBill = fromCursor(cursor);

                list.add(dailyBill);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != cursor){
                cursor.close();
            }
        }
        return list;
    }

    public static void delete(long bid){
        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        db.delete(DBNAME , "bid = ? " , new String[]{String.valueOf(bid)});
    }

    private static DailyBill fromCursor(Cursor cursor){
        long bid = CursorHelper.getLong(cursor, "bid");
        long billtime = CursorHelper.getLong(cursor, "billtime");
        long ctime = CursorHelper.getLong(cursor, "ctime");
        String remarks = CursorHelper.getString(cursor, "remarks");
        int industryId = CursorHelper.getInt(cursor, "industryId");
        int marketId = CursorHelper.getInt(cursor, "marketId");

        DailyBill dailyBill =  new DailyBill();
        dailyBill.setBid(bid);
        dailyBill.setBilltime(billtime);
        dailyBill.setCtime(ctime);
        dailyBill.setRemarks(remarks);
        dailyBill.setIndustryId(industryId);
        dailyBill.setMarketId(marketId);

        return dailyBill;
    }

    private static final String DBNAME = "dailyBillInfo";

    public static void createTable(SQLiteDatabase db){
        DbTableHelper.fromTableName(DBNAME)
                .addColumn_Long("bid")
                .addColumn_Long("billtime")
                .addColumn_Long("ctime")
                .addColumn_Varchar("remarks" , 20)
                .addColumn_Integer("industryId")
                .addColumn_Integer("marketId")

                .buildTable(db);
    }

    public static void dropTable(SQLiteDatabase db){
        db.execSQL("drop table if exists " + DBNAME);
    }


}
