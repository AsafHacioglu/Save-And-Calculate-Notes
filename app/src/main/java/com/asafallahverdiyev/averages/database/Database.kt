package com.asafallahverdiyev.averages.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context,"database",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE notes (subject_id INTEGER PRIMARY KEY AUTOINCREMENT, subject_name TEXT, subject_score REAL, subject_credit INTEGER);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }
}