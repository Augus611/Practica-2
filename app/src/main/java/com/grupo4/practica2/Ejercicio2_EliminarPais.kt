package com.grupo4.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Ejercicio2_EliminarPais : AppCompatActivity() {

    lateinit var ciudadesDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2_eliminar_pais)

        ciudadesDBHelper = miSQLiteHelper(this)
    }

    fun EliminarPais(view: View){

        val inputPais = findViewById<EditText>(R.id.input_PaisAEliminar)
        val PaisParametro = inputPais.text.toString()

        var cantidadeliminados = 0

        if(PaisParametro.isNotBlank()){

            cantidadeliminados= ciudadesDBHelper.EliminarPais(PaisParametro)

            if(cantidadeliminados > 0){
                Toast.makeText(this, "País eliminado exitosamente.", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "No se ha encontrado el país ingresado.", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Se debe ingresar un país.", Toast.LENGTH_SHORT).show()
        }
    }
}