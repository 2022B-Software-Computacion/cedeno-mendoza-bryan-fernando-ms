package com.example.fsmapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula(
   private val contexto: GRecyclerView,
   private val lista: ArrayList<BEntrenador>,
   private val recyclerView: RecyclerView
) : RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>(){
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionButton: Button
        var numeroLikes = 0
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButton = view.findViewById<Button>(R.id.btn_dar_like)
            accionButton.setOnClickListener { anadirLike() }
        }
        fun anadirLike() {
            numeroLikes = numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
            //recyclerView.adapter?.notifyDataSetChanged()
        }


    }

    // Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,  //en este caso el layout que vamosa a usar es el que creamos con los text view y el boton
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    // Setear los datos para la iteración
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //la lista llega de parametro en la parte de arriba
        val entrenadorActual = this.lista[position] //se va llenando dependiendo de la pocicion de la lista
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.descripcion
        holder.accionButton.text = "Like ${entrenadorActual.nombre}"  //Modificamos el texto del boton
        holder.likesTextView.text = "0" //Inicializamos loslikes en cero
    }

    //tamaño del arreglo
    override fun getItemCount(): Int {
        return this.lista.size      //Cuantas veces tengo que iterar la funcion OnBindViewHolder
    }



}