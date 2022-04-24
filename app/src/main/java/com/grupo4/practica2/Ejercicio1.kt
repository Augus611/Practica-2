package com.grupo4.practica2

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class Ejercicio1 : AppCompatActivity() {
    private var intentos = 5
    private var puntajeActual = 0
    private lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)
        sharedPreferences = getSharedPreferences("puntajeMaximo", Context.MODE_PRIVATE)
        val puntajeMaximo = sharedPreferences.getInt("puntajeMaximo", 0)
        val textViewPuntajeMaximo = findViewById<TextView>(R.id.textViewPuntajeMaximo)
        textViewPuntajeMaximo.text = puntajeMaximo.toString()
        actualizarPuntajeActual()
    }
    fun numeroAleatorio(view: View) {
        // Genera un número aleatorio del 1 al 5 y lo compara con el ingresado por el usuario.
        val numeroAleatorio = (1..5).random()
        val editTextNumeroUsuario = findViewById<EditText>(R.id.input_numero)
        val numeroUsuario = editTextNumeroUsuario.text.toString().toIntOrNull() ?: 0
        val textViewInstrucciones = findViewById<TextView>(R.id.textViewInstrucciones)
        val textViewIntentos = findViewById<TextView>(R.id.textViewIntentos)
        if (numeroUsuario !in 5 downTo 1) {
            Toast.makeText(this,"El número ingresado debe estar entre 1 y 5.",Toast.LENGTH_SHORT).show()
        } else {
            if (numeroAleatorio == numeroUsuario) {
                puntajeActual += 10
                actualizarPuntajeActual()
                intentos = 5
                textViewInstrucciones.text = "¡Acertaste!"
                textViewIntentos.text = "Te quedan $intentos intentos."
            } else {
                intentos--
                if (intentos != 1) {
                    textViewIntentos.text = "Te quedan $intentos intentos."
                } else {
                    textViewIntentos.text = "Te queda 1 intento."
                }
                textViewIntentos.visibility = View.VISIBLE
                textViewInstrucciones.text = "No acertaste, el número era $numeroAleatorio"
                if (intentos == 0) {
                    val puntajeMaximo = sharedPreferences.getInt("puntajeMaximo", 0)
                    if (puntajeActual > puntajeMaximo) {
                        val edit = sharedPreferences.edit()
                        edit.putInt("puntajeMaximo", puntajeActual)
                        edit.apply()
                        actualizarPuntajeActual()
                        findViewById<TextView>(R.id.textViewPuntajeMaximo).text = puntajeActual.toString()
                        Toast.makeText(this, "¡Nuevo puntaje máximo!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Fin del juego.",Toast.LENGTH_SHORT).show()
                    }
                    puntajeActual = 0
                    actualizarPuntajeActual()
                    intentos = 5
                    textViewIntentos.visibility = View.INVISIBLE
                }
            }
        }
        editTextNumeroUsuario.setText("")
        return
    }
    fun actualizarPuntajeActual() {
        findViewById<TextView>(R.id.textViewPuntajeActual).text = puntajeActual.toString()
    }
}