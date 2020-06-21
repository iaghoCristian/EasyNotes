package com.example.easynotes

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.NullPointerException
import java.util.*



class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handle = Handler()
        val postDelayed = handle.postDelayed(
            Runnable {
                kotlin.run { mostrarLogin() }
            },2000
        )

    }

    private fun mostrarLogin() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}