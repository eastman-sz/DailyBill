package com.bill.billbook

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.bill.db.CursorHelper
import com.bill.db.DbTableHelper
import com.bill.db.ISqliteDataBase
import java.util.ArrayList

class BillBookDbHelper {

    companion object {

        fun save(bookName: String) {
            val values = ContentValues()
            values.put("bookId", System.currentTimeMillis() / 1000)
            values.put("name", bookName)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME, null, values)
        }

        fun saveDefault(bookName: String) {
            val values = ContentValues()
            values.put("bookId", 1)
            values.put("name", bookName)

            val db = ISqliteDataBase.getSqLiteDatabase()
            db.insert(DBNAME, null, values)
        }

        fun getBillBooks(): ArrayList<BillBook> {
            val list = ArrayList<BillBook>()
            var cursor: Cursor? = null
            try {
                val db = ISqliteDataBase.getSqLiteDatabase()
                cursor = db.query(DBNAME, null, null, null, null, null, null, null)
                while (null != cursor && cursor.moveToNext()) {
                    list.add(fromCursor(cursor))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cursor?.close()
            }
            return list
        }

        private fun fromCursor(cursor: Cursor): BillBook {
            val bookId = CursorHelper.getLong(cursor, "bookId")
            val name = CursorHelper.getString(cursor, "name")

            val billBook = BillBook()
            billBook.bookId = bookId
            billBook.name = name
            return billBook
        }

        fun delete(bookId: Long) {
            val db = ISqliteDataBase.getSqLiteDatabase()
            db.delete(DBNAME, "bookId = ? ", arrayOf(bookId.toString()))
        }

        private val DBNAME = "billBookInfo"

        fun createTable(db: SQLiteDatabase) {
            DbTableHelper.fromTableName(DBNAME)
                    .addColumn_Long("bookId")
                    .addColumn_Varchar("name", 20)

                    .buildTable(db)
        }

        fun dropTable(db: SQLiteDatabase) {
            db.execSQL("drop table if exists $DBNAME")
        }

    }

}