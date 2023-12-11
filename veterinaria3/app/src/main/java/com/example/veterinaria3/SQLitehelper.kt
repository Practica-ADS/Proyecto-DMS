package com.example.veterinaria3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLitehelper(context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION =1
        private const val DATABASE_NAME="Veterinaria.db"
        private const val TBL_PACIENTES ="tbl_pacientes"
        private const val ID ="id"
        private const val Name = "Name"
        private const val raza = "raza"
        private  const val encargado = "encargado"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val cratetblPacientes = ("CREATE TABLE"+ TBL_PACIENTES + " ( " + ID + " INTEGER PRIMARY KEY , "+
                Name+" TEXT , "+ raza+"text , "+ encargado+ " text "+" ) ")
        db?.execSQL(cratetblPacientes)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_PACIENTES")
        onCreate(db)
    }

    fun insertPaciente(std: PacienteModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(Name, std.name)
        contentValues.put(raza, std.raza)
        contentValues.put(encargado, std.encargado)

        val success = db.insert(TBL_PACIENTES, null, contentValues)
        db.close()
        return success

    }
    @SuppressLint("Range")
    fun getAllPacientes(): ArrayList<PacienteModel>{
        val stdList: ArrayList<PacienteModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_PACIENTES"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
                cursor = db.rawQuery(selectQuery, null)


        }catch (e: Exception){

            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var raza: String
        var encargado: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("Name"))
                raza = cursor.getString(cursor.getColumnIndex("raza"))
                encargado = cursor.getString(cursor.getColumnIndex("encargado"))
                val std = PacienteModel(id = id, name=name, raza=raza, encargado=encargado)
                stdList.add(std)
            }while (cursor.moveToNext())

        }
        return stdList
    }
}