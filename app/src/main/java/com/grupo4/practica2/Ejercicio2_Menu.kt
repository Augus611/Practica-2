package com.grupo4.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Ejercicio2_Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2_menu)
    }

    fun ejercicio2 (view: View) {
        val intent = Intent(this, Ejercicio2::class.java)
        startActivity(intent)
    }

    fun ejercicio2_consulta (view: View) {
        val intent = Intent(this, ejercicio2_Consulta::class.java)
        startActivity(intent)
    }

    fun ejercicio2_Pais (view: View) {
        val intent = Intent(this, ejercicio2_EliminarPais::class.java)
        startActivity(intent)
    }
}