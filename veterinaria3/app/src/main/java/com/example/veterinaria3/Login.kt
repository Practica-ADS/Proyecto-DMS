package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var loginbtn: Button
    private lateinit var edituser: EditText
    private lateinit var editpword: EditText
    private lateinit var dbh: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginbtn = findViewById(R.id.button4)
        edituser = findViewById(R.id.editTextText2)
        editpword = findViewById(R.id.editTextTextPassword3)
        dbh = DBHelper(this)

        loginbtn.setOnClickListener {

            val useredtx = edituser.text.toString()
            val passedtx = editpword.text.toString()

            if (TextUtils.isEmpty(useredtx) || TextUtils.isEmpty(passedtx)){
                Toast.makeText(this, "Add Username & Password", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkuser = dbh.checkuserpass(useredtx, passedtx)
                if (checkuser == true){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, Menu::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Wrong username & Password", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}