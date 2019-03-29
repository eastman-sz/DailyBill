package com.bill.consumption.nature

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import java.lang.Exception

class NatureInfoDbHelper {

    companion object {

        fun save(natureName : String){
            val values = ContentValues()
            values.put("natureId" , getMaxNatureId() + 1)
            values.put("natureName" , natureName)
            values.put("cTime" , System.currentTimeMillis()/1000)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME, null , values)
        }

        fun getNatures() : List<NatureInfo>{
            val list = ArrayList<NatureInfo>()
            var cursor : Cursor ?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null , null, null , null , null , null)
                while (null != cursor &&cursor.moveToNext()){
                    list.add(fromCursor(cursor))
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return list
        }

        private fun getMaxNatureId(): Int {
            var typeId = 0
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, "natureId desc")
                if (null != cursor && cursor.moveToNext()) {
                    cursor.moveToFirst()
                    typeId = CursorHelper.getInt(cursor, "natureId")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return typeId
        }

        private fun fromCursor(cursor: Cursor) : NatureInfo{
            var natureId = CursorHelper.getInt(cursor , "natureId")
            var natureName = CursorHelper.getString(cursor , "natureName")
            var cTime = CursorHelper.getLong(cursor , "cTime")
            var updateTime = CursorHelper.getLong(cursor , "updateTime")

            val natureInfo = NatureInfo()
            natureInfo.natureId = natureId
            natureInfo.natureName = natureName
            natureInfo.cTime = cTime
            natureInfo.updateTime = updateTime
            return natureInfo
        }

        private val DBNAME = "natureInfoDbInfo"

        fun createTable(db: SQLiteDatabase) {
            DbTableHelper.fromTableName(DBNAME)
                    .addColumn_Integer("natureId")
                    .addColumn_Varchar("natureName" , 20)
                    .addColumn_Long("cTime")
                    .addColumn_Long("updateTime")
                    .buildTable(db)
        }

        fun dropTable(db: SQLiteDatabase) {
            db.execSQL("drop table if exists $DBNAME")
        }


    }

}