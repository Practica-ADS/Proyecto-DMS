package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Doctores : AppCompatActivity() {
    private lateinit var btn10: Button
    private lateinit var btn11: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctores)

        btn10 = findViewById(R.id.button10)
        btn11 = findViewById(R.id.button11)
        btn10.setOnClickListener{

            val i = Intent(this,Registrar_Doctores::class.java)
            startActivity(i)
        }
        btn11.setOnClickListener{

            val i = Intent(this,Doctores::class.java)
            startActivity(i)
        }
    }
}