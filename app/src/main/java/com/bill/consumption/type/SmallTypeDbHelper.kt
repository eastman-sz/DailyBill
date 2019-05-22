package com.bill.consumption.type

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.SparseArray
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import com.bill.util.BroadcastAction
import com.bill.util.BroadcastUtil
import java.lang.Exception

class SmallTypeDbHelper {

    companion object {

        fun save(superType : Int ,bigTypeId : Int , typeName : String){
            val values = ContentValues()
            values.put("superType" , superType)
            values.put("bigTypeId" , bigTypeId)
            values.put("typeId" , getMaxTypeId(superType) + 1)
            values.put("typeName" , typeName)
            values.put("cTime" , System.currentTimeMillis()/1000)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME , null , values)
        }

        //只在初始化时用
        fun save(superType : Int , bigTypeId : Int , typeId : Int , typeName : String){
            val values = ContentValues()
            values.put("superType" , superType)
            values.put("bigTypeId" , bigTypeId)
            values.put("typeId" , typeId)
            values.put("typeName" , typeName)
            values.put("cTime" , System.currentTimeMillis()/1000)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME , null , values)
        }

        fun updateTypeName(superType : Int , typeId : Int , typeName : String){
            val values = ContentValues()
            values.put("superType" , superType)
            values.put("typeId" , typeId)
            values.put("typeName" , typeName)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.update(DBNAME , values , "typeId = ? " , arrayOf(typeId.toString()))
            //发送广播
            BroadcastUtil.sendBroadCast(BroadcastAction.smallTypeFresh)
        }

        fun getSmallTypeS(superType : Int , bigTypeId: Int) : List<SmallType>{
            val list = ArrayList<SmallType>()
            var cursor : Cursor?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME , null , "superType = ? and bigTypeId = ? ", arrayOf(superType.toString(), bigTypeId.toString()) , null , null , null)
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

        private fun getMaxTypeId(superType : Int): Int {
            var typeId = 0
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, "superType = ? ", arrayOf(superType.toString()), null, null, "typeId desc")
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

        fun getSmallType(superType : Int , typeId : Int) : SmallType?{
            var smallType : SmallType ?= null
            var cursor : Cursor ?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null , "superType = ? and typeId = ? ", arrayOf(superType.toString() , typeId.toString()) , null , null , null)
                if (null != cursor &&cursor.moveToNext()){
                    cursor.moveToFirst()
                    smallType = fromCursor(cursor)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return smallType
        }

        fun getNameArray(superType: Int) : SparseArray<String>{
            val array = SparseArray<String>()
            var cursor : Cursor ?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null , "superType = ? ", arrayOf(superType.toString()) , null , null , null)
                while (null != cursor && cursor.moveToNext()){
                    val smallType = fromCursor(cursor)
                    array.put(smallType.typeId , smallType.typeName)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return array
        }

        //删除一级分类的时候，底下的二级分类也要一并删除
        fun deleteAll(bigTypeId: Int){
            val db = ISqliteDataBase.getSqLiteDatabase()
            db.delete(DBNAME , "bigTypeId = ? " , arrayOf(bigTypeId.toString()))
            //发送广播
            BroadcastUtil.sendBroadCast(BroadcastAction.smallTypeFresh)
        }

        fun delete(typeId : Int){
            val db = ISqliteDataBase.getSqLiteDatabase()
            db.delete(DBNAME , "typeId = ? " , arrayOf(typeId.toString()))
            //发送广播
            BroadcastUtil.sendBroadCast(BroadcastAction.smallTypeFresh)
        }

        private fun fromCursor(cursor: Cursor) : SmallType{
            var superType = CursorHelper.getInt(cursor , "superType")
            var bigTypeId = CursorHelper.getInt(cursor , "bigTypeId")
            var typeId = CursorHelper.getInt(cursor , "typeId")
            var typeName = CursorHelper.getString(cursor , "typeName")
            var cTime = CursorHelper.getLong(cursor , "cTime")
            var updateTime = CursorHelper.getLong(cursor , "updateTime")

            val smallType = SmallType()
            smallType.superType = superType
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
                    .addColumn_Integer("superType")
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