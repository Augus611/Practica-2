package com.grupo4.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast

class Ejercicio2_Alta : AppCompatActivity() {

    lateinit var ciudadesDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2_alta)

        ciudadesDBHelper = miSQLiteHelper(this)
        val inputPais = findViewById<AutoCompleteTextView>(R.id.inputPaisAlta)
        val paises = ciudadesDBHelper.devolverPaises()
        ArrayAdapter(this, android.R.layout.simple_list_item_1, paises).also { adapter -> inputPais.setAdapter(adapter)}
    }

    fun crearCiudad(view: View){
        val inputCiudad = findViewById<EditText>(R.id.inputCiudadAlta)
        val inputPoblacion = findViewById<EditText>(R.id.inputPoblacionAlta)
        val inputPais = findViewById<AutoCompleteTextView>(R.id.inputPaisAlta)
        val pais = inputPais.text.toString()
        val ciudad = inputCiudad.text.toString()
        val poblacion = inputPoblacion.text.toString().toIntOrNull() ?: -1

        if (ciudadesDBHelper.searchCiudad(ciudad) == null) {
            if (pais.isNotBlank() && ciudad.isNotBlank() && poblacion != -1) {
                ciudadesDBHelper.addDato(ciudad, pais, poblacion)

                inputPais.text.clear()
                inputCiudad.text.clear()
                inputPoblacion.text.clear()

                Toast.makeText(this, "Guardado.", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "No puede haber campos vacíos.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "La ciudad ingresada ya existe.", Toast.LENGTH_SHORT).show()
        }
    }
}