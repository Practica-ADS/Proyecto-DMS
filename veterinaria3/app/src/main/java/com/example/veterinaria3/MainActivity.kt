package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnsignup: Button
    private lateinit var btnlogin: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnsignup = findViewById(R.id.button2)
        btnlogin = findViewById(R.id.button)

        btnsignup.setOnClickListener {
            val intent = Intent(this, registrarse::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}