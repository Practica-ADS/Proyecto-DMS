package com.example.veterinaria3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen =  installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreen.setKeepOnScreenCondition{true}
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}