package com.example.veterinaria3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class Registrarcita : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edfecha: EditText
    private  lateinit var edhora: EditText
    private  lateinit var edencargado: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnview: Button
    private lateinit var btnUpdate: Button

    private lateinit var sqlitehelper: SQLitehelper3
    private lateinit var recyclerView: RecyclerView
    private  var adapter: Citaadapter?=null
    private var std:CitaModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarcita)


        initview()
        initRecyclerview()
        sqlitehelper = SQLitehelper3(this)

        btnAdd.setOnClickListener { addCita() }
        btnview.setOnClickListener { getCita() }
        btnUpdate.setOnClickListener { updateCita() }
        adapter?.setOnclickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()

            edName.setText(it.name)
            edfecha.setText(it.fecha)
            edhora.setText(it.hora)
            edencargado.setText(it.encargado)
            std = it

        }
        adapter?.setOnclickDeleteItem {
            deleteCita(it.id)
        }

    }

    private fun getCita(){

        val stdlist = sqlitehelper.getAllCitas()
        Log.e("pppp", "${stdlist.size}")

        adapter?.addItems(stdlist)
    }

    private fun addCita(){

        val name = edName.text.toString()
        val fecha = edfecha.text.toString()
        val hora= edhora.text.toString()
        val encargado= edencargado.text.toString()

        if (name.isEmpty() || fecha.isEmpty() || hora.isEmpty()||encargado.isEmpty()){
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }
        else{
            val std = CitaModel(name=name, fecha = fecha, hora = hora, encargado = encargado)
            val status = sqlitehelper.insertCita(std)

            if (status > -1){
                Toast.makeText(this, "cita added", Toast.LENGTH_SHORT ).show()
                clearEditText()
                getCita()
            }
            else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCita(){

        val name = edName.text.toString()
        val fecha = edfecha.text.toString()
        val hora= edhora.text.toString()
        val encargado= edencargado.text.toString()

        if (name == std?.name && fecha ==std?.fecha && hora == std?.hora && encargado == std?.encargado){
            Toast.makeText(this, "record not changed", Toast.LENGTH_SHORT).show()
            return
        }
        if (std == null)return


        val std = CitaModel(id = std!!.id, name = name, fecha = fecha, hora = hora, encargado=encargado)
        val status = sqlitehelper.UpdateCita(std)

        if (status > -1){
            clearEditText()
        }
        else{
            Toast.makeText(this,    "Update failed", Toast.LENGTH_SHORT ).show()
        }

    getCita()

    }

    private fun deleteCita(id:Int){


        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item")
        builder.setCancelable(true)
        builder.setPositiveButton("yes"){ dialog, _ ->
            sqlitehelper.deletecitaById(id)
            getCita()

            dialog.dismiss()

        }
        builder.setNegativeButton("No"){ dialog, _ ->

            dialog.dismiss()

        }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText() {
        edName.setText("")
        edfecha.setText("")
        edhora.setText("")
        edencargado.setText("")
        edName.requestFocus()
    }

    private  fun initRecyclerview(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Citaadapter()
        recyclerView.adapter = adapter
    }

    private fun initview(){

        edName = findViewById(R.id.editTextText10)
        edfecha = findViewById(R.id.editTextText11)
        edhora = findViewById(R.id.editTextText12)
        edencargado = findViewById(R.id.editTextText13)
        btnAdd = findViewById(R.id.button21)
        btnview = findViewById(R.id.button22)
        btnUpdate =findViewById(R.id.button23)
        recyclerView = findViewById(R.id.recycleview3)
    }


}