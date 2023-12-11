package com.example.veterinaria3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class Agregarpacientes : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edraza: EditText
    private  lateinit var edencargado: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnview: Button

    private lateinit var SQLitehelper: SQLitehelper
    private lateinit var recyclerView: RecyclerView
    private  var adapter: Pacienteadapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarpacientes)

        initview()
        initRecyclerview()
        SQLitehelper = SQLitehelper(this)

        btnAdd.setOnClickListener { addpaciente() }
        btnview.setOnClickListener { getpaciente() }
        adapter?.setOnclickItem { Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show() }

    }

    private fun getpaciente(){

        val stdlist = SQLitehelper.getAllPacientes()
        Log.e("pppp", "${stdlist.size}")

        adapter?.addItems(stdlist)
    }

    private fun addpaciente(){

        val name = edName.text.toString()
        val raza = edraza.text.toString()
        val encargado= edencargado.text.toString()

        if (name.isEmpty() || raza.isEmpty() || encargado.isEmpty()){
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }
        else{
            val std = PacienteModel(name=name, raza = raza, encargado = encargado)
            val status = SQLitehelper.insertPaciente(std)

            if (status > -1){
                Toast.makeText(this, "patience added", Toast.LENGTH_SHORT ).show()
                clearEditText()
                getpaciente()
            }
            else{
            Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        edName.setText("")
        edraza.setText("")
        edencargado.setText("")
        edName.requestFocus()
    }

    private  fun initRecyclerview(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Pacienteadapter()
        recyclerView.adapter = adapter
    }

    private fun initview(){

        edName = findViewById(R.id.editTextText4)
        edraza = findViewById(R.id.editTextText5)
        edencargado = findViewById(R.id.editTextText6)
        btnAdd = findViewById(R.id.button15)
        btnview = findViewById(R.id.button16)
        recyclerView = findViewById(R.id.recycleview)
    }
}