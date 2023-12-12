package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Citas : AppCompatActivity() {
    private lateinit var btn12: Button
    private lateinit var btn13: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas)

        btn12 = findViewById(R.id.button12)
        btn13 = findViewById(R.id.button13)

        btn12.setOnClickListener{
            val i = Intent(this,Registrarcita::class.java)
            startActivity(i)
        }
        btn13.setOnClickListener{
            val i = Intent(this,Citas::class.java)
            startActivity(i)
        }
    }
}