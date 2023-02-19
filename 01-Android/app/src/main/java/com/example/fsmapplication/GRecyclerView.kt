package com.example.fsmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes=0
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        //Inicializamos Entrenadores
        val listaEntrenador= arrayListOf<BEntrenador>()
        listaEntrenador
            .add(BEntrenador(1,"Juan","Paredes"))
        listaEntrenador
            .add(BEntrenador(1,"Juan2","Paredes"))
        listaEntrenador
            .add(BEntrenador(1,"Juan3","Paredes"))
        listaEntrenador
            .add(BEntrenador(1,"Juan4","Paredes"))
        //Instaciamos el recyclerView creado en la interface
        val recyclerView= findViewById<RecyclerView>(R.id.rv_entrenadores)
        //Mandamos a llamar a la funcion inicializar recycler con el recycler y la lista de entrenadores
        inicializarRecyclerView(listaEntrenador,recyclerView)
    }
    fun inicializarRecyclerView(
        lista:ArrayList<BEntrenador>,
        recyclerView: RecyclerView
    ){ //En el listview tambien teniamos un adaptador con un array
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter=adaptador //Creamos un adaptador
        recyclerView.itemAnimator= androidx.recyclerview.widget.DefaultItemAnimator()//Configuramos la animacion
        recyclerView.layoutManager= androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged() //Actualizamos la interface
    }
    fun aumentarTotalLikes(){
        totalLikes=totalLikes+1 //Aumentamos los likes
        val totalLikesTextView= findViewById<TextView>(R.id.tv_total_likes) //instanciamos el textview del total de likes
        totalLikesTextView.text=totalLikes.toString()       //Cambiamos el texto del textView por el total de likes
    }
}

