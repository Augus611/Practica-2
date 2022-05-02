package com.grupo4.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Ejercicio2_Consulta : AppCompatActivity() {

    lateinit var ciudadesDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2_consulta)

        ciudadesDBHelper = miSQLiteHelper(this)
    }

    fun buscarCiudad(view: View){

        val inputBusqueda= findViewById<EditText>(R.id.inputBusqueda)

        val inputPais = findViewById<EditText>(R.id.inputPaisConsulta)
        val inputCiudad = findViewById<EditText>(R.id.inputCiudadConsulta)
        val inputPoblacion = findViewById<EditText>(R.id.inputPoblacionConsulta)

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

    fun EliminarCiudad(view: View){

        val inputCiudad = findViewById<EditText>(R.id.inputBusqueda)
        val ciudadParametro = inputCiudad.text.toString()

        when {
            ciudadesDBHelper.searchCiudad(ciudadParametro) != null -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("¿Seguro que querés eliminar $ciudadParametro?")
                    .setCancelable(false)
                    .setPositiveButton("Sí") { _, _ ->
                        ciudadesDBHelper.EliminarCiudad(ciudadParametro)
                        Toast.makeText(this, "Ciudad eliminada exitosamente.", Toast.LENGTH_LONG).show()
                        inputCiudad.text.clear()
                        findViewById<EditText>(R.id.inputPaisConsulta).text.clear()
                        findViewById<EditText>(R.id.inputCiudadConsulta).text.clear()
                        findViewById<EditText>(R.id.inputPoblacionConsulta).text.clear()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
            ciudadParametro.isBlank() -> {
                Toast.makeText(this, "Se debe ingresar una ciudad.", Toast.LENGTH_SHORT).show()
            }
            ciudadesDBHelper.searchCiudad(ciudadParametro) == null -> {
                Toast.makeText(this, "No se ha encontrado la ciudad ingresada.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun ModificarPoblacion(view: View){

        val inputCiudad = findViewById<EditText>(R.id.inputCiudadConsulta)
        val inputPoblacion = findViewById<EditText>(R.id.inputPoblacionConsulta)
        val ciudadParametro = inputCiudad.text.toString()
        val poblacion = inputPoblacion.text.toString().toIntOrNull() ?: -1

        if(ciudadParametro.isNotBlank() && poblacion != -1){
            ciudadesDBHelper.ActualizarDato(ciudadParametro, poblacion)
            Toast.makeText(this, "Población actualizada exitosamente.", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "No se pudo actualizar.", Toast.LENGTH_LONG).show()
        }

    }


}