package com.grupo4.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class ejercicio2_Consulta : AppCompatActivity() {

    lateinit var ciudadesDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2_consulta)

        ciudadesDBHelper = miSQLiteHelper(this)
    }

    fun buscarCiudad(view: View){

        val inputBusqueda= findViewById<EditText>(R.id.inputBusqueda)

        val inputPais = findViewById<EditText>(R.id.inputPais)
        val inputCiudad = findViewById<EditText>(R.id.inputCiudad)
        val inputPoblacion = findViewById<EditText>(R.id.inputPoblacion)

        val busqueda = inputBusqueda.text.toString()

        if(busqueda.isNotBlank()){
            val resultado = ciudadesDBHelper.searchCiudad(busqueda)
            if (resultado != null) {
                inputPais.text.clear()
                inputPais.text.append(resultado.elementAt(1))
                inputCiudad.text.clear()
                inputCiudad.text.append(resultado.elementAt(0))
                inputPoblacion.text.clear()
                inputPoblacion.text.append(resultado.elementAt(2))
            } else {
                Toast.makeText(this, "No se ha encontrado la ciudad ingresada.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}