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

        if(pais.isNotBlank() && ciudad.isNotBlank()){
            ciudadesDBHelper.addDato(ciudad, pais, poblacion)

            inputPais.text.clear()
            inputCiudad.text.clear()
            inputPoblacion.text.clear()

            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
            
        }else{
            Toast.makeText(this, "Complete los datos", Toast.LENGTH_LONG).show()
        }
    }

    fun buscarCiudad(view: View){
        //No funciona
        val inputBusqueda= findViewById<EditText>(R.id.inputBusqueda)

        val inputPais = findViewById<EditText>(R.id.inputPais)
        val inputCiudad = findViewById<EditText>(R.id.inputCiudad)
        val inputPoblacion = findViewById<EditText>(R.id.inputPoblacion)

        val busqueda = inputBusqueda.text.toString()

        if(busqueda.isNotBlank()){
            val db:SQLiteDatabase = ciudadesDBHelper.readableDatabase

            val cursor = db.rawQuery("SELECT * FROM ciudades WHERE nombre = busqueda", null)

            if(cursor.moveToFirst()){
                do{
                    inputCiudad.text.append(cursor.getString(1).toString())
                    inputPais.text.append(cursor.getString(2).toString())
                    inputPoblacion.text.append(cursor.getInt(3).toString())
                }while (cursor.moveToNext())
            }
        }

    }
}