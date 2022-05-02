package com.grupo4.practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Ejercicio2_EliminarPais : AppCompatActivity() {

    lateinit var ciudadesDBHelper: miSQLiteHelper
    lateinit var inputPais : AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2_eliminar_pais)

        ciudadesDBHelper = miSQLiteHelper(this)
        inputPais = findViewById(R.id.input_PaisAEliminar)
        actualizarDropDown()
        inputPais.setOnFocusChangeListener { _, _ -> if (hasWindowFocus()) {inputPais.showDropDown() } }
        val botonEliminar = findViewById<Button>(R.id.button_EliminarPais)
        botonEliminar.setOnClickListener {
            eliminarPais()
        }
    }
    fun eliminarPais(){

        val paisParametro = inputPais.text.toString()

        when {
            ciudadesDBHelper.existePais(paisParametro) -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("¿Seguro que querés eliminar ${inputPais.text}?")
                    .setCancelable(false)
                    .setPositiveButton("Sí") { _, _ ->
                        ciudadesDBHelper.EliminarPais(paisParametro)
                        Toast.makeText(this, "País eliminado exitosamente.", Toast.LENGTH_LONG).show()
                        actualizarDropDown()
                        inputPais.text.clear()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
            paisParametro == "" -> {
                Toast.makeText(this, "Se debe ingresar un país.", Toast.LENGTH_SHORT).show()
            }
            !ciudadesDBHelper.existePais(paisParametro) -> {
                Toast.makeText(this, "No se ha encontrado el país ingresado.", Toast.LENGTH_SHORT).show()
            }

        }
    }
    fun actualizarDropDown() {
        val paises = ciudadesDBHelper.devolverPaises()
        ArrayAdapter(this, android.R.layout.simple_list_item_1, paises).also { adapter -> inputPais.setAdapter(adapter)}
    }
}