package com.grupo4.practica2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class miSQLiteHelper(context: Context):SQLiteOpenHelper(context, "ciudades.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE ciudades" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT, pais TEXT, poblacion INTEGER)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, olderVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS ciudades"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun addDato(nombre: String, pais: String, poblacion: Int){
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("pais", pais)
        datos.put("poblacion", poblacion)

        val db = this.writableDatabase
        db.insert("ciudades",null,datos)
        db.close()
    }

}
