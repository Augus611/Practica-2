package com.grupo4.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class Ejercicio1 : AppCompatActivity() {
    private var intentos = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)
    }
    fun numeroAleatorio(view: View) {
        // Genero un número aleatorio entre 1 y 5
        val numeroAleatorio = (1..5).random()
        // Obtengo el número ingresado por el usuario. Si no ingresó número, lo igualo a 0.
        val numeroUsuario = findViewById<EditText>(R.id.input_numero).text.toString().toIntOrNull() ?: 0
        // Comparo ambos números
        if (numeroAleatorio == numeroUsuario) {
            // sumar 10 puntos
            intentos = 5
            return
        } else {
            intentos--
            if (intentos == 0) {
                // terminar el juego
                intentos = 5
                return
            }
        }
    }
}