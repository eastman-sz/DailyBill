package com.bill.point;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import com.bill.db.CursorHelper;
import com.bill.db.DbTableHelper;
import com.bill.db.ISqliteDataBase;
import java.util.ArrayList;
/**
 * Created by E on 2018/3/12.
 */
public class ConsumptionPointDbHelper {

    public static void add(String marketName){
        ContentValues values = new ContentValues();
        values.put("marketName" , marketName);
        values.put("marketId" , getMaxMarketId() + 1);
        long ctime = System.currentTimeMillis()/1000;
        values.put("ctime" , ctime);
        values.put("updateTime" , ctime);

        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        db.insert(DBNAME , null , values);
    }

    private static int getMaxMarketId(){
        int pointId = 0;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , null , null , null , null , "marketId desc");
            if (null != cursor && cursor.moveToNext()){
                cursor.moveToFirst();
                pointId = CursorHelper.getInt(cursor , "marketId");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != cursor){
                cursor.close();
            }
        }
        return pointId;
    }

    public static ArrayList<ConsumptionPoint> getAllConsuptionPoints(){
        ArrayList<ConsumptionPoint> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , null , null , null , null , null);
            while (null != cursor && cursor.moveToNext()){
                list.add(fromCursor(cursor));
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

    public static ArrayList<String> getAllConsuptionPointNames(){
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , null , null , null , null , null);
            while (null != cursor && cursor.moveToNext()){
                list.add(fromCursor(cursor).getMarketName());
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

    public static SparseArray<ConsumptionPoint> getAllConsuptionPointsArray(){
        SparseArray<ConsumptionPoint> array = new SparseArray<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null , null , null , null , null , null);
            while (null != cursor && cursor.moveToNext()){
                ConsumptionPoint point = fromCursor(cursor);

                array.put(point.getMarketId() , point);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != cursor){
                cursor.close();
            }
        }
        return array;
    }

    private static ConsumptionPoint fromCursor(Cursor cursor){
        int industryId = CursorHelper.getInt(cursor , "industryId");
        String industryName = CursorHelper.getString(cursor , "industryName");
        int marketId = CursorHelper.getInt(cursor , "marketId");
        String marketName = CursorHelper.getString(cursor , "marketName");
        long ctime = CursorHelper.getLong(cursor , "ctime");
        long updateTime = CursorHelper.getLong(cursor , "updateTime");

        ConsumptionPoint point = new ConsumptionPoint();
        point.setIndustryId(industryId);
        point.setIndustryName(industryName);
        point.setMarketId(marketId);
        point.setMarketName(marketName);
        point.setCtime(ctime);
        point.setUpdateTime(updateTime);

        return point;
    }

    private static final String DBNAME = "consumptionPointInfo";

    public static void createTable(SQLiteDatabase db){
        DbTableHelper.fromTableName(DBNAME)
                .addColumn_Integer("industryId")
                .addColumn_Varchar("industryName" , 10)
                .addColumn_Integer("marketId")
                .addColumn_Varchar("marketName" , 20)
                .addColumn_Long("ctime")
                .addColumn_Long("updateTime")

                .buildTable(db);
    }

    public static void dropTable(SQLiteDatabase db){
        db.execSQL("drop table if exists " + DBNAME);
    }


}
