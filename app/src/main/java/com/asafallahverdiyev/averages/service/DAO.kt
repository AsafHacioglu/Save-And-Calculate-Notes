package com.asafallahverdiyev.averages.service

import android.content.ContentValues
import com.asafallahverdiyev.averages.database.Database
import com.asafallahverdiyev.averages.models.NoteModel


class DAO {

    fun insertData(database: Database, subjectName: String, subjectScore: Float, subjectCredit: Int) {
        val db = database.writableDatabase
        val values = ContentValues()
        values.put("subject_name",subjectName)
        values.put("subject_score",subjectScore)
        values.put("subject_credit",subjectCredit)
        db.insertOrThrow("notes",null,values)
        db.close()
    }

    fun updateData(database: Database, subject_id: Int, subjectName: String, subjectScore: Float, subjectCredit: Int) {
        val db = database.writableDatabase
        val values = ContentValues()
        values.put("subject_name",subjectName)
        values.put("subject_score",subjectScore)
        values.put("subject_credit",subjectCredit)
        db.update("notes",values,"subject_id=?", arrayOf(subject_id.toString()))
        db.close()
    }

    fun getData(database: Database) : ArrayList<NoteModel> {
        val list = ArrayList<NoteModel>()
        val db = database.writableDatabase
        val console = db.rawQuery("SELECT * FROM notes",null)
        while (console.moveToNext()) {
            val note = NoteModel(console.getInt(console.getColumnIndex("subject_id")),
                console.getString(console.getColumnIndex("subject_name")),
                console.getFloat(console.getColumnIndex("subject_score")),
                console.getInt(console.getColumnIndex("subject_credit")))
            list.add(note)
        }
        db.close()
        console.close()
        return list
    }

    fun deleteData(database: Database, subject_id: Int) {
        val db = database.writableDatabase
        db.delete("notes","subject_id=?",arrayOf(subject_id.toString()))
        db.close()
    }

    fun sortAscending(database: Database) : ArrayList<NoteModel> {
        val list = ArrayList<NoteModel>()
        val db = database.writableDatabase
        val console = db.rawQuery("SELECT * FROM notes ORDER BY subject_score ASC",null)
        while (console.moveToNext()) {
            val note = NoteModel(console.getInt(console.getColumnIndex("subject_id")),
                console.getString(console.getColumnIndex("subject_name")),
                console.getFloat(console.getColumnIndex("subject_score")),
                console.getInt(console.getColumnIndex("subject_credit")))
            list.add(note)
        }
        db.close()
        console.close()
        return list
    }

    fun sortDescending(database: Database) : ArrayList<NoteModel> {
        val list = ArrayList<NoteModel>()
        val db = database.writableDatabase
        val console = db.rawQuery("SELECT * FROM notes ORDER BY subject_score DESC",null)
        while (console.moveToNext()) {
            val note = NoteModel(console.getInt(console.getColumnIndex("subject_id")),
                console.getString(console.getColumnIndex("subject_name")),
                console.getFloat(console.getColumnIndex("subject_score")),
                console.getInt(console.getColumnIndex("subject_credit")))
            list.add(note)
        }
        db.close()
        console.close()
        return list
    }




}