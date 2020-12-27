package com.example.myapplicationoop

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Elektronik.db",null,1) {
    companion object {
        private var instance: DBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context) : DBHelper {
            if (instance == null){
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Laptop.TABLE_LAPTOP, true,
        Laptop.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
        Laptop.MERK to TEXT,
        Laptop.TYPE to TEXT,
        Laptop.HARGA to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Laptop.TABLE_LAPTOP, true)
    }

}

val Context.database : DBHelper
get() = DBHelper.getInstance(applicationContext)