package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class registrarse : AppCompatActivity() {

    private lateinit var uname: EditText
    private lateinit var pword: EditText
    private lateinit var cpword: EditText
    private lateinit var signupbtn: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        uname= findViewById(R.id.editTextText)
        pword= findViewById(R.id.editTextTextPassword)
        cpword= findViewById(R.id.editTextTextPassword2)
        signupbtn= findViewById(R.id.button3)
        db = DBHelper(this)

        signupbtn.setOnClickListener {
            val unametext = uname.text.toString()
            val pwordtext = pword.text.toString()
            val cpwordtext = cpword.text.toString()
            val savedata = db.insertdata(unametext, pwordtext)

            if (TextUtils.isEmpty(unametext) || TextUtils.isEmpty(pwordtext) || TextUtils.isEmpty(cpwordtext)){
                Toast.makeText(this, "Add username password & confirm Password", Toast.LENGTH_SHORT).show()
            }
            else{
                if (pwordtext.equals(cpwordtext)){
                    if (savedata== true){
                        Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "User Exist", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}