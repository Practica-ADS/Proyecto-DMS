package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Pacientes : AppCompatActivity() {
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)

        btn8 = findViewById(R.id.button8)
        btn9 = findViewById(R.id.button9)
        btn8.setOnClickListener{

            val i = Intent(this,Agregarpacientes::class.java)
            startActivity(i)
        }
        btn9.setOnClickListener{

            val i = Intent(this,Pacientes::class.java)
            startActivity(i)
        }
    }
}