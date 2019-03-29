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
public class DailyBillDbHelper {

    public static void save(float amount ,long billTime ,String remarks  ,int marketId , int bigTypeId , int smallTypeId , int natureId){
        ContentValues values = new ContentValues();
        long bid = System.currentTimeMillis();//唯一id
        values.put("bid" , bid);
        values.put("billTime" , billTime);
        values.put("ctime" , bid);
        values.put("remarks" , remarks);
        values.put("marketId" , marketId);
        values.put("amount" , amount);
        values.put("bigTypeId" , bigTypeId);
        values.put("smallTypeId" , smallTypeId);
        values.put("natureId" , natureId);

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
            cursor = db.query(DBNAME , null , null , null , null , null , "billtime desc");
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

    public static ArrayList<DailyBill> getAllDailyBills(long bookId){
        ArrayList<DailyBill> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , "bookId = ? "
                    , new String[]{String.valueOf(bookId)} , null , null , "billtime desc");
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

    public static ArrayList<DailyBill> getDailyBills(long bookId , long startTime , long endTime){
        ArrayList<DailyBill> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , "bookId = ? and billtime > ? and billtime < ? " ,
                    new String[]{String.valueOf(startTime) , String.valueOf(endTime)} , null , null , "billtime desc");
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
            cursor = db.query(DBNAME , null , "billTime > ? and billTime < ? " ,
                    new String[]{String.valueOf(startTime) , String.valueOf(endTime)} , null , null , "billtime desc");
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
        long bookId = CursorHelper.getLong(cursor, "bookId");
        long billTime = CursorHelper.getLong(cursor, "billTime");
        long ctime = CursorHelper.getLong(cursor, "ctime");
        String remarks = CursorHelper.getString(cursor, "remarks");
        int marketId = CursorHelper.getInt(cursor, "marketId");
        float amount = CursorHelper.getFloat(cursor, "amount");
        int bigTypeId = CursorHelper.getInt(cursor, "bigTypeId");
        int smallTypeId = CursorHelper.getInt(cursor, "smallTypeId");
        int natureId = CursorHelper.getInt(cursor, "natureId");

        DailyBill dailyBill =  new DailyBill();
        dailyBill.setBid(bid);
        dailyBill.setBookId(bookId);
        dailyBill.setBillTime(billTime);
        dailyBill.setCtime(ctime);
        dailyBill.setRemarks(remarks);
        dailyBill.setMarketId(marketId);
        dailyBill.setAmount(amount);
        dailyBill.setBigTypeId(bigTypeId);
        dailyBill.setSmallTypeId(smallTypeId);
        dailyBill.setNatureId(natureId);

        return dailyBill;
    }

    private static final String DBNAME = "dailyBillInfo";

    public static void createTable(SQLiteDatabase db){
        DbTableHelper.fromTableName(DBNAME)
                .addColumn_Long("bid")
                .addColumn_Long("bookId")
                .addColumn_Long("billtime")
                .addColumn_Long("ctime")
                .addColumn_Varchar("remarks" , 20)
                .addColumn_Integer("marketId")
                .addColumn_Float("amount")

                .addColumn_Integer("bigTypeId")
                .addColumn_Integer("smallTypeId")
                .addColumn_Integer("natureId")

                .buildTable(db);
    }

    public static void dropTable(SQLiteDatabase db){
        db.execSQL("drop table if exists " + DBNAME);
    }


}
