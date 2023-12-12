package com.example.veterinaria3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLitehelper3(context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION =1
        private const val DATABASE_NAME="Veterinaria3.db"
        private const val TBL_CITAS ="tbl_citas"
        private const val ID ="id"
        private const val Name = "Name"
        private const val fecha = "fecha"
        private  const val hora = "hora"
        private  const val encargado = "encargado"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createtblCitas = ("CREATE TABLE "+ TBL_CITAS + " ( " + ID + " INTEGER PRIMARY KEY , "+
                Name+" TEXT , "+ fecha+" text , "+ hora+ " text , "+  encargado+" text "+")" )
        db?.execSQL(createtblCitas)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_CITAS")
        onCreate(db)
    }

    fun insertCita(std: CitaModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(Name, std.name)
        contentValues.put(fecha, std.fecha)
        contentValues.put(hora, std.hora)
        contentValues.put(encargado, std.encargado)

        val success = db.insert(TBL_CITAS, null, contentValues)
        db.close()
        return success

    }
    @SuppressLint("Range")
    fun getAllCitas(): ArrayList<CitaModel>{
        val stdList: ArrayList<CitaModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_CITAS"
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
        var fecha: String
        var hora: String
        var encargado: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("Name"))
                fecha = cursor.getString(cursor.getColumnIndex("fecha"))
                hora = cursor.getString(cursor.getColumnIndex("hora"))
                encargado = cursor.getString(cursor.getColumnIndex("encargado"))
                val std = CitaModel(id = id, name=name, fecha=fecha, hora=hora, encargado=encargado)
                stdList.add(std)
            }while (cursor.moveToNext())

        }
        return stdList
    }

    fun UpdateCita(std: CitaModel): Int {
        val  db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(Name, std.name)
        contentValues.put(fecha, std.fecha)
        contentValues.put(hora, std.hora)
        contentValues.put(encargado, std.encargado)

        val success = db.update(TBL_CITAS, contentValues, "id=" + std.id, null)
        db.close()
        return success

    }

    fun deletecitaById(id:Int): Int{

        val db  = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_CITAS, "id=$id", null)
        db.close()
        return success
    }
}