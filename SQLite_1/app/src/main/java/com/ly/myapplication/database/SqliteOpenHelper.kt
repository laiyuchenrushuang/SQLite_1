package com.ly.myapplication.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by ly on 2019/7/5 11:43
 *
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
class SqliteOpenHelper(mContext: Context) : SQLiteOpenHelper(mContext, DB_NAME, null, VERSION) {
    private val CREATE_TABLE_SQL =
        "create table %s(id integer primary key autoincrement,name varchar(10),sex varchar(10),age varchar(10),zplj varchar(10))"
    private val CAREATE_TABLE = String.format(CREATE_TABLE_SQL, TABLE_NAME)

    companion object {
        @Volatile
        var instance: SqliteOpenHelper? = null
        private val DB_NAME = "codeTable.db"
        var TABLE_NAME = "CODE_TABLE"
        private val VERSION = 1

        fun getInstance(mContext: Context): SqliteOpenHelper? {
            if (instance == null) {
                synchronized(SqliteOpenHelper::class) {
                    if (instance == null) {
                        instance = SqliteOpenHelper(mContext)
                    }
                }
            }
            return instance
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("lylog","ssssssssss")
        db!!.execSQL(CAREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}