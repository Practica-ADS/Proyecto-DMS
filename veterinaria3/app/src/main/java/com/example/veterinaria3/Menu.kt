package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Menu : AppCompatActivity() {

    private lateinit var btn5:Button
    private lateinit var btn6:Button
    private lateinit var btn7: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        btn5 = findViewById(R.id.button5)
        btn6 = findViewById(R.id.button6)
        btn7 = findViewById(R.id.button7)

        btn5.setOnClickListener{

            val i = Intent(this,Pacientes::class.java)
            startActivity(i)
        }
        btn6.setOnClickListener{

            val i = Intent(this,Doctores::class.java)
            startActivity(i)
        }
        btn7.setOnClickListener{

            val i = Intent(this,Citas::class.java)
            startActivity(i)
        }
    }
}