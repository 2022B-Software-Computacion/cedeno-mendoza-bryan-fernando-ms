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

    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura=writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(        //eliminar en la tabla entrenador
                "ENTRENADOR", //NOMBRE DE TABLA
                "id=?",        //Consulta where
                arrayOf(
                    id.toString()
                )//Parametros
            )
        conexionEscritura.close() //Cerramos la coneccion de la base de datos
        return if(resultadoEliminacion.toInt() == -1) false else true // retornamos el estado de la accion
    }

    fun actualizarEntrenadorFormulario(
        nombre:String,
        descripcion: String,
        idActualizar:Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura
            .update(  // Accion a realizar
                "ENTRENADOR",
                valoresActualizar,
                "id=?", //Clausula Where
                arrayOf(
                    idActualizar.toString() //parametro a comparar
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1)false else true
    }

    fun consultarEntrenadorPorId(id: Int):BEntrenador{
        //val baseDatosLecutra = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM ENTRENADOR WHERE ID =?" //ponemos la consulta con los parametros que necesitemos
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario, //script de Consulta
            arrayOf(
                id.toString()
            )//Parametros de la consulta
        )
        //Logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst() //Nos movemos al primero dato de la consulta
        var usuarioEncontrado = BEntrenador(0,"","") //Crearemos un entrenador

        val arreglo = arrayListOf<BEntrenador>()//En el caso de que lo que querramos es un arerglo de entrenadores
                                                //y luego podemos agregar cada entrenador  al array
        if(existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0)//columna indice 0->id
                val nombre = resultadoConsultaLectura.getString(1) //Columna indice 1->Nombre
                val descripcion  = resultadoConsultaLectura.getString(2) //columna indice 2->descripción
                if(id != null){
                   // usuarioEncontrado = BEntrenador(0,"","")//si lo que se instancia es el arreglo
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    arreglo.add(usuarioEncontrado)
                }
            }while (resultadoConsultaLectura.moveToNext()) //mientras nos podemos mover al sig instancia
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }









}