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

class Agregarpacientes : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edraza: EditText
    private lateinit var edencargado: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnview: Button
    private lateinit var btnUpdate: Button

    private lateinit var sqlitehelper: SQLitehelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: Pacienteadapter? = null
    private var std: PacienteModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarpacientes)

        initview()
        initRecyclerview()
        sqlitehelper = SQLitehelper(this)

        btnAdd.setOnClickListener { addpaciente() }
        btnview.setOnClickListener { getpaciente() }
        btnUpdate.setOnClickListener { updatePaciente() }
        adapter?.setOnclickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()

            edName.setText(it.name)
            edraza.setText(it.raza)
            edencargado.setText(it.encargado)
            std = it

        }
        adapter?.setOnclickDeleteItem {
            deletePaciente(it.id)
        }

    }

    private fun getpaciente() {

        val stdlist = sqlitehelper.getAllPacientes()
        Log.e("pppp", "${stdlist.size}")

        adapter?.addItems(stdlist)
    }

    private fun addpaciente() {

        val name = edName.text.toString()
        val raza = edraza.text.toString()
        val encargado = edencargado.text.toString()

        if (name.isEmpty() || raza.isEmpty() || encargado.isEmpty()) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val std = PacienteModel(name = name, raza = raza, encargado = encargado)
            val status = sqlitehelper.insertPaciente(std)

            if (status > -1) {

                Toast.makeText(this, "patience added", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, status.toString(), Toast.LENGTH_SHORT).show()
                clearEditText()
                getpaciente()
            } else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatePaciente() {

        val name = edName.text.toString()
        val raza = edraza.text.toString()
        val encargado = edencargado.text.toString()

        if (name == std?.name && raza == std?.raza && encargado == std?.encargado) {
            Toast.makeText(this, "record not changed", Toast.LENGTH_SHORT).show()
            return
        }
        if (std == null) return


        val std = PacienteModel(id = std!!.id, name = name, raza = raza, encargado = encargado)
        val status = sqlitehelper.UpdatePaciente(std)

        if (status > -1) {
            clearEditText()
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
        }

        getpaciente()

    }

    private fun deletePaciente(id: Int) {


        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item")
        builder.setCancelable(true)
        builder.setPositiveButton("yes") { dialog, _ ->
            sqlitehelper.deletepacienteById(id)
            getpaciente()

            dialog.dismiss()

        }
        builder.setNegativeButton("No") { dialog, _ ->

            dialog.dismiss()

        }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText() {
        edName.setText("")
        edraza.setText("")
        edencargado.setText("")
        edName.requestFocus()
    }

    private fun initRecyclerview() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Pacienteadapter()
        recyclerView.adapter = adapter
    }

    private fun initview() {

        edName = findViewById(R.id.editTextText4)
        edraza = findViewById(R.id.editTextText5)
        edencargado = findViewById(R.id.editTextText6)
        btnAdd = findViewById(R.id.button15)
        btnview = findViewById(R.id.button16)
        btnUpdate = findViewById(R.id.button17)
        recyclerView = findViewById(R.id.recycleview)
    }
}