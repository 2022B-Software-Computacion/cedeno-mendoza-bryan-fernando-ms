package com.example.fsmapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?,
): SQLiteOpenHelper(
    contexto,
    "moviles", //nombre BDD
    null,
    1
) {//Extendemos la clase con SQliteOpenHelper e implementamos los miembros
    override fun onCreate(db: SQLiteDatabase?) {
    val scriptSQLCrearTablaEntrenador = //Las tres comillas me permiten poner un gran bloque de codigo
        //en esta seccion solo ponemos sql puro como en cualquier consulta
        """   
                CREATE TABLE ENTRENADOR( 
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                nombre VARCHR(50),
                descripcion VARCHAR(50)
                )
        """.trimIndent()
    db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ):Boolean{
        val baseDatosEscritura = writableDatabase // vamos a usar una base de datos de Escritura
        val valorsAGuardar = ContentValues() //Esta clase Contenvalue me permite poner llave valor de cada registro.
        valorsAGuardar.put("nombre", nombre) //agregamos variable valor
        valorsAGuardar.put("descripcion", descripcion)
        val resultadoGuardar = baseDatosEscritura
            .insert(        //Inserta en la tabla entrenador
                "ENTRENADOR", //NOMBRE DE TABLA
                null,
                valorsAGuardar //valores a guardar
            )
        baseDatosEscritura.close() //Cerramos la coneccion de la base de datos
        return if(resultadoGuardar.toInt() == -1) false else true // retornamos el estado de la accion
    }

    


}