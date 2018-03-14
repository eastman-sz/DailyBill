package com.bill.billbook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bill.db.CursorHelper;
import com.bill.db.DbTableHelper;
import com.bill.db.ISqliteDataBase;
import java.util.ArrayList;
/**
 * Created by E on 2018/3/14.
 */
public class BillbookDbHelper {

    public static void save(String bookName){
        ContentValues values = new ContentValues();
        values.put("bookId" , System.currentTimeMillis()/1000);
        values.put("name" , bookName);

        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        db.insert(DBNAME , null , values);
    }

    public static ArrayList<Billbook> getBillbooks(){
        ArrayList<Billbook> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
            cursor = db.query(DBNAME , null, null, null, null, null, null, null);
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

    private static Billbook fromCursor(Cursor cursor){
        long bookId = CursorHelper.getLong(cursor , "bookId");
        String name = CursorHelper.getString(cursor , "name");

        Billbook billbook = new Billbook();
        billbook.setBookId(bookId);
        billbook.setName(name);
        return billbook;
    }

    public static void delete(long bookId){
        SQLiteDatabase db = ISqliteDataBase.getSqLiteDatabase();
        db.delete(DBNAME , "bookId = ? " , new String[]{String.valueOf(bookId)});
    }

    private static final String DBNAME = "billBookInfo";

    public static void createTable(SQLiteDatabase db){
        DbTableHelper.fromTableName(DBNAME)
                .addColumn_Long("bookId")
                .addColumn_Varchar("name" , 20)

                .buildTable(db);
    }

    public static void dropTable(SQLiteDatabase db){
        db.execSQL("drop table if exists " + DBNAME);
    }

}
