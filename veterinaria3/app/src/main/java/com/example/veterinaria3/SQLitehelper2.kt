package com.example.veterinaria3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLitehelper2(context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION =1
        private const val DATABASE_NAME="Veterinaria2.db"
        private const val TBL_DOCTORES ="tbl_doctores"
        private const val ID ="id"
        private const val Name = "Name"
        private const val puesto = "puesto"
        private  const val area = "area"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createtblDoctores = ("CREATE TABLE "+ TBL_DOCTORES + " ( " + ID + " INTEGER PRIMARY KEY , "+
                Name+" TEXT , "+ puesto+" text , "+ area+ " text "+" ) ")
        db?.execSQL(createtblDoctores)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_DOCTORES")
        onCreate(db)
    }

    fun insertDoctor(std: DoctorModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(Name, std.name)
        contentValues.put(puesto, std.puesto)
        contentValues.put(area, std.area)

        val success = db.insert(TBL_DOCTORES, null, contentValues)
        db.close()
        return success

    }
    @SuppressLint("Range")
    fun getAllDoctores(): ArrayList<DoctorModel>{
        val stdList: ArrayList<DoctorModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_DOCTORES"
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
        var puesto: String
        var area: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("Name"))
                puesto = cursor.getString(cursor.getColumnIndex("puesto"))
                area = cursor.getString(cursor.getColumnIndex("area"))
                val std = DoctorModel(id = id, name=name, puesto=puesto, area=area)
                stdList.add(std)
            }while (cursor.moveToNext())

        }
        return stdList
    }

    fun UpdateDoctor(std: DoctorModel): Int {
        val  db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(Name, std.name)
        contentValues.put(puesto, std.puesto)
        contentValues.put(area, std.area)

        val success = db.update(TBL_DOCTORES, contentValues, "id=" + std.id, null)
        db.close()
        return success

    }

    fun deletedoctorById(id:Int): Int{

        val db  = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_DOCTORES, "id=$id", null)
        db.close()
        return success
    }
}