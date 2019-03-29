package com.bill.consumption.type

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastUtil
import java.lang.Exception

class BigTypeDbHelper {

    companion object {

        fun save(typeName : String){
            val values = ContentValues()
            values.put("typeId" , getMaxTypeId() + 1)
            values.put("typeName" , typeName)
            values.put("cTime" , System.currentTimeMillis()/1000)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME , null , values)
        }

        //只在初始化时用
        fun save(typeId : Int , typeName : String){
            val values = ContentValues()
            values.put("typeId" , typeId)
            values.put("typeName" , typeName)
            values.put("cTime" , System.currentTimeMillis()/1000)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME , null , values)
        }

        fun updateTypeName(typeId : Int , typeName : String){
            val values = ContentValues()
            values.put("typeId" , typeId)
            values.put("typeName" , typeName)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.update(DBNAME , values , "typeId = ? " , arrayOf(typeId.toString()))

            //发送广播
            BroadcastUtil.sendBroadCast(BroadcastAction.bigTypeFresh)
        }

        fun getBigTypeS() : List<BigType>{
            val list = ArrayList<BigType>()
            var cursor : Cursor ?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME , null , null, null , null , null , null)
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

        fun getBigType(typeId : Int) : BigType?{
            var bigType : BigType ?= null
            var cursor : Cursor ?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME , null , "typeId = ? ", arrayOf(typeId.toString()) , null , null , null)
                if (null != cursor &&cursor.moveToNext()){
                    cursor.moveToFirst()
                    bigType = fromCursor(cursor)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return bigType
        }

        private fun getMaxTypeId(): Int {
            var typeId = 0
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, "typeId desc")
                if (null != cursor && cursor.moveToNext()) {
                    cursor.moveToFirst()
                    typeId = CursorHelper.getInt(cursor, "typeId")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return typeId
        }

        fun delete(typeId : Int){
            val db = ISqliteDataBase.getSqLiteDatabase()
            db.delete(DBNAME , "typeId = ? " , arrayOf(typeId.toString()))
            //发送广播
            BroadcastUtil.sendBroadCast(BroadcastAction.bigTypeFresh)
            //删除相应的子项
            SmallTypeDbHelper.deleteAll(typeId)
        }

        private fun fromCursor(cursor: Cursor) : BigType{
            var typeId = CursorHelper.getInt(cursor , "typeId")
            var typeName = CursorHelper.getString(cursor , "typeName")
            var cTime = CursorHelper.getLong(cursor , "cTime")
            var updateTime = CursorHelper.getLong(cursor , "updateTime")

            val bigType = BigType()
            bigType.typeId = typeId
            bigType.typeName = typeName
            bigType.cTime = cTime
            bigType.updateTime = updateTime
            return bigType
        }

        private val DBNAME = "bigTypeDbInfo"

        fun createTable(db: SQLiteDatabase) {
            DbTableHelper.fromTableName(DBNAME)
                    .addColumn_Integer("typeId")
                    .addColumn_Varchar("typeName" , 20)
                    .addColumn_Long("cTime")
                    .addColumn_Long("updateTime")
                    .buildTable(db)
        }

        fun dropTable(db: SQLiteDatabase) {
            db.execSQL("drop table if exists $DBNAME")
        }


    }


}