package com.bill.bill

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.bill.consumption.payment.Payment
import com.bill.daylist.DailyBillFilter
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import java.util.ArrayList

class DailyBillDbHelper {

    companion object {

        fun save(bookId: Long , amount: Float, billTime: Long, remarks: String, marketId: Int, bigTypeId: Int,
                 smallTypeId: Int, natureId: Int , paymentId: Int) {
            val values = ContentValues()
            values.put("bookId", bookId)
            val bid = System.currentTimeMillis()//唯一id
            values.put("bid", bid)
            values.put("billTime", billTime)
            values.put("cTime", bid)
            values.put("remarks", remarks)
            values.put("marketId", marketId)
            values.put("amount", amount)
            values.put("bigTypeId", bigTypeId)
            values.put("smallTypeId", smallTypeId)
            values.put("natureId", natureId)
            values.put("paymentId", paymentId)

            val db = ISqliteDataBase.getSqLiteDatabase()
            val count = db.update(DBNAME, values, "bid = ? ", arrayOf(bid.toString()))
            if (count < 1) {
                db.insert(DBNAME, null, values)
            }
        }

        //所有帐本下的数据
        fun getAllDailyBills(): ArrayList<DailyBill> {
            val list = ArrayList<DailyBill>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, "billTime desc")
                while (null != cursor && cursor.moveToNext()) {
                    val dailyBill = fromCursor(cursor)

                    list.add(dailyBill)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return list
        }

        //某一帐本下的所有数据
        fun getAllDailyBills(bookId: Long): ArrayList<DailyBill> {
            val list = ArrayList<DailyBill>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, "bookId = ? ", arrayOf(bookId.toString()), null, null, "billTime desc")
                while (null != cursor && cursor.moveToNext()) {
                    val dailyBill = fromCursor(cursor)

                    list.add(dailyBill)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return list
        }

        //某一帐本下的某一时间段的数据
        fun getDailyBills(bookId: Long, startTime: Long, endTime: Long): ArrayList<DailyBill> {
            val list = ArrayList<DailyBill>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, "bookId = ? and billTime > ? and billTime < ? ",
                        arrayOf(startTime.toString(), endTime.toString()), null, null, "billTime desc")
                while (null != cursor && cursor.moveToNext()) {
                    val dailyBill = fromCursor(cursor)

                    list.add(dailyBill)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return list
        }

        //某一时间段的数据
        fun getDailyBills(startTime: Long, endTime: Long): ArrayList<DailyBill> {
            val list = ArrayList<DailyBill>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, "billTime > ? and billTime < ? ",
                        arrayOf(startTime.toString(), endTime.toString()), null, null, "billTime desc")
                while (null != cursor && cursor.moveToNext()) {
                    val dailyBill = fromCursor(cursor)

                    list.add(dailyBill)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return list
        }

        //根据过滤条件来筛选数据
        fun getFilterDailyBill(it : DailyBillFilter) : List<DailyBill>{
            var selection = ""
            val agsList = ArrayList<String>()
            if (it.startTimestamp > 0 && it.endTimestamp > 0){
                selection = selection.plus("billTime > ? and billTime < ? ")

                agsList.add(it.startTimestamp.toString())
                agsList.add(it.endTimestamp.toString())
            }
            if (it.bookId > 0){
                selection = selection.plus("and bookId = ? ")

                agsList.add(it.bookId.toString())
            }
            if (it.marketId > 0){
                selection = selection.plus("and marketId = ? ")

                agsList.add(it.marketId.toString())
            }
            if (it.bigTypeId > 0){
                selection = selection.plus("and bigTypeId = ? and smallTypeId = ? ")

                agsList.add(it.bigTypeId.toString())
                agsList.add(it.smallTypeId.toString())
            }
            if (it.natureId > 0){
                selection = selection.plus("and natureId = ? ")

                agsList.add(it.natureId.toString())
            }
            if (it.paymentId > 0){
                selection = selection.plus("and paymentId = ? ")

                agsList.add(it.paymentId.toString())
            }
            if (selection.startsWith("and ")){
                selection = selection.substring(4)
            }

            var whereArgs = agsList.toTypedArray()
            val list = ArrayList<DailyBill>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, selection, whereArgs, null, null, "billTime desc")
                while (null != cursor && cursor.moveToNext()) {
                    val dailyBill = fromCursor(cursor)

                    list.add(dailyBill)
                }
            }catch (e : java.lang.Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            return list
        }

        //某一时间段的数据统计
        fun getDailyBillAmount(startTime: Long, endTime: Long): Float {
            var totalAmount = 0F
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.rawQuery("select sum(amount) AS amount from ".plus(DBNAME).plus(" where billTime > ? and billTime < ? ")  , arrayOf(startTime.toString(), endTime.toString()))
                if (null != cursor && cursor.moveToNext()){
                    cursor.moveToFirst()
                    totalAmount = CursorHelper.getFloat(cursor , "amount")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return totalAmount
        }

        //删除某一帐本下的数据
        fun deleteBook(bookId: Long) {
            val db = ISqliteDataBase.getSqLiteDatabase()
            db.delete(DBNAME, "bookId = ? ", arrayOf(bookId.toString()))
        }

        //删除某一条
        fun delete(bid: Long) {
            val db = ISqliteDataBase.getSqLiteDatabase()
            db.delete(DBNAME, "bid = ? ", arrayOf(bid.toString()))
        }

        private fun fromCursor(cursor: Cursor) : DailyBill{
            var bid = CursorHelper.getLong(cursor , "bid")
            var bookId = CursorHelper.getLong(cursor , "bookId")
            var amount = CursorHelper.getFloat(cursor , "amount")
            var billTime = CursorHelper.getLong(cursor , "billTime")
            var cTime = CursorHelper.getLong(cursor , "cTime")
            var remarks = CursorHelper.getString(cursor , "remarks")
            var marketId = CursorHelper.getInt(cursor , "marketId")

            var bigTypeId = CursorHelper.getInt(cursor , "bigTypeId")
            var smallTypeId = CursorHelper.getInt(cursor , "smallTypeId")
            var natureId = CursorHelper.getInt(cursor , "natureId")
            var paymentId = CursorHelper.getInt(cursor , "paymentId")

            val dailyBill = DailyBill()
            dailyBill.bid = bid
            dailyBill.bookId = bookId
            dailyBill.amount = amount
            dailyBill.billTime = billTime
            dailyBill.cTime = cTime
            dailyBill.remarks = remarks
            dailyBill.marketId = marketId
            dailyBill.bigTypeId = bigTypeId
            dailyBill.smallTypeId = smallTypeId
            dailyBill.natureId = natureId
            dailyBill.paymentId = paymentId

            return dailyBill
        }

        private val DBNAME = "dailyBillInfo"

        fun createTable(db: SQLiteDatabase) {
            DbTableHelper.fromTableName(DBNAME)
                    .addColumn_Long("bid")
                    .addColumn_Long("bookId")
                    .addColumn_Float("amount")
                    .addColumn_Long("billTime")
                    .addColumn_Long("cTime")
                    .addColumn_Varchar("remarks", 60)
                    .addColumn_Integer("marketId")
                    .addColumn_Integer("bigTypeId")
                    .addColumn_Integer("smallTypeId")
                    .addColumn_Integer("natureId")
                    .addColumn_Integer("paymentId")

                    .buildTable(db)
        }

        fun dropTable(db: SQLiteDatabase) {
            db.execSQL("drop table if exists $DBNAME")
        }


    }

}