package com.example.a02_examen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_examen.R
import com.example.a02_examen.Sistema
import com.google.firebase.firestore.FirebaseFirestore

class SistemaAdapter(private val sistemaList: ArrayList<Sistema>) :
    RecyclerView.Adapter<SistemaAdapter.SistemaViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class SistemaViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nombreSistema: TextView = itemView.findViewById(R.id.tvTitleNameSistemaContent)
        val fechaDescubrimiento: TextView = itemView.findViewById(R.id.tvTitleFechaDescubrimientoContent)
        val distancia: TextView = itemView.findViewById(R.id.tvTitleDistanciaContent)
        val masaSolar: TextView = itemView.findViewById(R.id.tvTitleMasaSolarContent)
        val temperatura: TextView = itemView.findViewById(R.id.tvTitleTemperaturaContent)
        val habitable : TextView = itemView.findViewById(R.id.tvTitleHabitableContent)
        val btnEliminarSistema : Button = itemView.findViewById(R.id.btnSistemaDelete)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SistemaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_view_sistema,
            parent, false)
        return  SistemaViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int = sistemaList.size

    override fun onBindViewHolder(holder: SistemaViewHolder, position: Int) {
        val item = sistemaList[position]
        holder.nombreSistema.text = sistemaList[position].nombre
        holder.fechaDescubrimiento.text = sistemaList[position].fechaDescubrimiento
        holder.distancia.text = sistemaList[position].distancia.toString()
        holder.masaSolar.text = sistemaList[position].masaSolar.toString()
        holder.temperatura.text = sistemaList[position].temperatura.toString()
        holder.habitable.text = sistemaList[position].habitable.toString()

        holder.btnEliminarSistema.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val activity = it.context as AppCompatActivity
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete")
            builder.setMessage("Estas seguro de eliminar este Sistema Planetario?")
            builder.setPositiveButton("Yes"){ dialogInterface, i: Int ->
                val deleteItem = db.collection("Sistema").document(item.id)
                println(item.id)
                db.runBatch {batch ->
                    batch.delete(deleteItem)
                }.addOnCompleteListener {
                    Toast.makeText(activity, "Eliminacion Existosa", Toast.LENGTH_SHORT).show()
                    sistemaList.removeAt(position)
                    notifyDataSetChanged()
                }
            }
            builder.setNegativeButton("No"){ dialogInterface, i: Int ->
                println("No seleccionado")
            }
            builder.show()
        }

    }
}