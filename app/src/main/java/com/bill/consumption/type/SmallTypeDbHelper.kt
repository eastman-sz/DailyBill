package com.bill.consumption.type

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import java.lang.Exception

class SmallTypeDbHelper {

    companion object {

        fun save(bigTypeId : Int , typeName : String){
            val values = ContentValues()
            values.put("bigTypeId" , bigTypeId)
            values.put("typeId" , getMaxTypeId() + 1)
            values.put("typeName" , typeName)
            values.put("cTime" , System.currentTimeMillis()/1000)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME , null , values)
        }

        //只在初始化时用
        fun save(bigTypeId : Int , typeId : Int , typeName : String){
            val values = ContentValues()
            values.put("bigTypeId" , bigTypeId)
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
        }

        fun getSmallTypeS() : List<SmallType>{
            val list = ArrayList<SmallType>()
            var cursor : Cursor?= null
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

        fun getSmallTypeS(bigTypeId: Int) : List<SmallType>{
            val list = ArrayList<SmallType>()
            var cursor : Cursor?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME , null , "bigTypeId = ? ", arrayOf(bigTypeId.toString()) , null , null , null)
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

        private fun fromCursor(cursor: Cursor) : SmallType{
            var bigTypeId = CursorHelper.getInt(cursor , "bigTypeId")
            var typeId = CursorHelper.getInt(cursor , "typeId")
            var typeName = CursorHelper.getString(cursor , "typeName")
            var cTime = CursorHelper.getLong(cursor , "cTime")
            var updateTime = CursorHelper.getLong(cursor , "updateTime")

            val smallType = SmallType()
            smallType.bigTypeId = bigTypeId
            smallType.typeId = typeId
            smallType.typeName = typeName
            smallType.cTime = cTime
            smallType.updateTime = updateTime
            return smallType
        }

        private val DBNAME = "smallTypeDbInfo"

        fun createTable(db: SQLiteDatabase) {
            DbTableHelper.fromTableName(DBNAME)
                    .addColumn_Integer("bigTypeId")
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