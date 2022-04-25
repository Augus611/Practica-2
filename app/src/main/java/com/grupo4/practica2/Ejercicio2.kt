package com.grupo4.practica2

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Ejercicio2 : AppCompatActivity() {

    lateinit var ciudadesDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        ciudadesDBHelper = miSQLiteHelper(this)

    }

    fun crearCiudad(view: View){
        val inputPais = findViewById<EditText>(R.id.inputPais)
        val inputCiudad = findViewById<EditText>(R.id.inputCiudad)
        val inputPoblacion = findViewById<EditText>(R.id.inputPoblacion)

        val pais = inputPais.text.toString()
        val ciudad = inputCiudad.text.toString()
        val poblacion = inputPoblacion.text.toString().toInt()

        if (ciudadesDBHelper.searchCiudad(ciudad) == null) {
            if (pais.isNotBlank() && ciudad.isNotBlank() && poblacion.toString().isNotBlank()) {
                ciudadesDBHelper.addDato(ciudad, pais, poblacion)

                inputPais.text.clear()
                inputCiudad.text.clear()
                inputPoblacion.text.clear()

                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "La ciudad ingresada ya existe.", Toast.LENGTH_SHORT).show()
        }
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