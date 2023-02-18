package com.example.fsmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view) //traemos el listView
        val adaptador = ArrayAdapter(   //creamos un arreglo de entrenadores
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador  //Creamos un adaptador
        adaptador.notifyDataSetChanged()  //Actualizamos la interface
        val botonAnadirListView = findViewById<Button>(  // Escuchamos el boton añadir
            R.id.btn_anadir_list_view)
        botonAnadirListView
            .setOnClickListener {
                anadirEntrenador(adaptador)         //seteamos los datos
            }
    }
    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>  //Llega un adaptador que es un array de entredadores
    ){
        arreglo.add(
            BEntrenador(    //añadimos  un entrenador al arreglo
                "Adrian",
                "Descripcion"
            )
        )
        adaptador.notifyDataSetChanged()   //
    }
}