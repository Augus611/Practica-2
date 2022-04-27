package com.grupo4.practica2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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

    fun searchCiudad(nombre: String): List<String>? {
        val db = this.readableDatabase
        val fila = db.rawQuery("SELECT nombre, pais, poblacion FROM ciudades where nombre = ?", arrayOf(nombre))
        return if (fila.moveToFirst()) {
            listOf(fila.getString(0), fila.getString(1), fila.getString(2))
        } else {
            null
        }
    }

    fun EliminarCiudad(nombre: String): Int{

        val arg = arrayOf(nombre.toString())

        val db = this.writableDatabase
        val borradosC = db.delete("ciudades","nombre = ?", arg)
        db.close()

        return borradosC

    }

    fun EliminarPais(pais: String): Int{

        val arg = arrayOf(pais.toString())

        val db = this.writableDatabase
        val borradosP = db.delete("ciudades","pais = ?", arg)
        db.close()

        return borradosP

    }


    fun ActualizarDato(nombre: String, poblacion: Int){

        val arg = arrayOf(nombre.toString())

        val datos = ContentValues()
        datos.put("poblacion", poblacion)

        val db = this.writableDatabase
        db.update("ciudades", datos,"nombre = ?", arg )
        db.close()
    }
}
