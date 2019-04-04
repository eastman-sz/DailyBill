package com.bill.consumption.payment

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.SparseArray
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import java.lang.Exception

class PaymentDbHelper {

    companion object {

        fun save(paymentName : String){
            val values = ContentValues()
            values.put("paymentId" , getMaxPaymentId() + 1)
            values.put("paymentName" , paymentName)
            values.put("cTime" , System.currentTimeMillis()/1000)
            values.put("updateTime" , System.currentTimeMillis()/1000)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME, null , values)
        }

        fun getPayments() : List<Payment>{
            val list = ArrayList<Payment>()
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

        fun getPaymentNameArray() : SparseArray<String>{
            val array = SparseArray<String>()
            var cursor : Cursor ?= null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null , null, null , null , null , null)
                while (null != cursor &&cursor.moveToNext()){
                    val it = fromCursor(cursor)
                    array.put(it.paymentId , it.paymentName)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return array
        }


        private fun getMaxPaymentId(): Int {
            var typeId = 0
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, "paymentId desc")
                if (null != cursor && cursor.moveToNext()) {
                    cursor.moveToFirst()
                    typeId = CursorHelper.getInt(cursor, "paymentId")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return typeId
        }

        private fun fromCursor(cursor: Cursor) : Payment {
            var paymentId = CursorHelper.getInt(cursor , "paymentId")
            var paymentName = CursorHelper.getString(cursor , "paymentName")
            var cTime = CursorHelper.getLong(cursor , "cTime")
            var updateTime = CursorHelper.getLong(cursor , "updateTime")

            val payment = Payment()
            payment.paymentId = paymentId
            payment.paymentName = paymentName
            payment.cTime = cTime
            payment.updateTime = updateTime
            return payment
        }

        private val DBNAME = "paymentInfoDbInfo"

        fun createTable(db: SQLiteDatabase) {
            DbTableHelper.fromTableName(DBNAME)
                    .addColumn_Integer("paymentId")
                    .addColumn_Varchar("paymentName" , 20)
                    .addColumn_Long("cTime")
                    .addColumn_Long("updateTime")
                    .buildTable(db)
        }

        fun dropTable(db: SQLiteDatabase) {
            db.execSQL("drop table if exists $DBNAME")
        }

    }


}