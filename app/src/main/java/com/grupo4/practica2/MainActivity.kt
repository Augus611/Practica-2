package com.grupo4.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ejercicio1 (view: View) {
        val intent = Intent(this, Ejercicio1::class.java)
        startActivity(intent)
    }

    fun ejercicio2 (view: View) {
        val intent = Intent(this, Ejercicio2_Menu::class.java)
        startActivity(intent)
    }
}