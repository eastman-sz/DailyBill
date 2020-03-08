package com.bill.consumption.martket

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.SparseArray
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import com.bill.util.BroadcastHelper
import java.lang.Exception

class MarketDbHelper {

    companion object {

        fun add(marketName: String) {
            val values = ContentValues()
            values.put("marketName", marketName)
            values.put("marketId", getMaxMarketId() + 1)
            val cTime = System.currentTimeMillis() / 1000
            values.put("cTime", cTime)
            values.put("updateTime", cTime)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME, null, values)

            //广播信息
            BroadcastHelper.onMarketChanged()
        }

        private fun getMaxMarketId(): Int {
            var pointId = 0
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, "marketId desc")
                if (null != cursor && cursor.moveToNext()) {
                    cursor.moveToFirst()
                    pointId = CursorHelper.getInt(cursor, "marketId")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return pointId
        }

        fun getMarketArray() : SparseArray<Market>{
            val array = SparseArray<Market>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, null)
                while (null != cursor && cursor.moveToNext()){
                    val market = fromCursor(cursor)
                    array.put(market.marketId , market)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return array
        }

        fun getMarketNameArray() : SparseArray<String>{
            val array = SparseArray<String>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, null)
                while (null != cursor && cursor.moveToNext()){
                    val market = fromCursor(cursor)
                    array.put(market.marketId , market.marketName)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return array
        }

        fun getAllMarketNames() : List<String>{
            val list = ArrayList<String>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, null)
                while (null != cursor && cursor.moveToNext()){
                    val market = fromCursor(cursor)
                    list.add(market.marketName!!)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return list
        }


        fun getMarkets() : List<Market>{
            val list = ArrayList<Market>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, null)
                while (null != cursor && cursor.moveToNext()){
                    list.add(fromCursor(cursor))
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return list
        }

        private fun fromCursor(cursor: Cursor) : Market{
            var marketId = CursorHelper.getInt(cursor , "marketId")
            var marketName = CursorHelper.getString(cursor , "marketName")
            var cTime: Long = CursorHelper.getLong(cursor , "cTime")
            var updateTime: Long = CursorHelper.getLong(cursor , "updateTime")

            val market = Market()
            market.marketId = marketId
            market.marketName = marketName
            market.cTime = cTime
            market.updateTime = updateTime

            return market
        }

        private val DBNAME = "marketDbInfo"

        fun createTable(db: SQLiteDatabase) {
            DbTableHelper.fromTableName(DBNAME)
                    .addColumn_Integer("marketId")
                    .addColumn_Varchar("marketName", 20)
                    .addColumn_Long("cTime")
                    .addColumn_Long("updateTime")

                    .buildTable(db)
        }

        fun dropTable(db: SQLiteDatabase) {
            db.execSQL("drop table if exists $DBNAME")
        }


    }

}