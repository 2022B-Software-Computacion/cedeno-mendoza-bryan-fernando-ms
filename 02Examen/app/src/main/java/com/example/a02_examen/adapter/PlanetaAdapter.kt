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
import com.example.a02_examen.Planeta
import com.example.a02_examen.R
import com.google.firebase.firestore.FirebaseFirestore

class PlanetaAdapter(private val planetaList: ArrayList<Planeta>) :
    RecyclerView.Adapter<PlanetaAdapter.PlanetaViewHolder>() {

    private lateinit var mListener: PlanetaAdapter.onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class PlanetaViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nombrePlaneta: TextView = itemView.findViewById(R.id.tvTitleNamePlanetaContent)
        val superficiePlaneta: TextView = itemView.findViewById(R.id.tvTitleSuperficiePlanetaContent)
        val habitablePlaneta : TextView =itemView.findViewById(R.id.tvTitleHabitablePlanetaContent)
        val fechaDesPlaneta: TextView = itemView.findViewById(R.id.tvTitleFechaDescubrimientoPlanetaContent)
        val edadPlaneta: TextView = itemView.findViewById(R.id.tvTitleEdadPlanetaContent)
        val btnDeletePlaneta : Button = itemView.findViewById(R.id.btnPlanetaDelete)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlanetaAdapter.PlanetaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_view_planeta,
            parent, false)
        return PlanetaAdapter.PlanetaViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int = planetaList.size

    override fun onBindViewHolder(holder: PlanetaAdapter.PlanetaViewHolder, position: Int) {
        val item = planetaList[position]
        holder.nombrePlaneta.text = planetaList[position].nombre
        holder.superficiePlaneta.text = planetaList[position].superficie.toString()
        holder.habitablePlaneta.text = planetaList[position].habitable.toString()
        holder.fechaDesPlaneta.text = planetaList[position].fechaDescubrimiento
        holder.edadPlaneta.text = planetaList[position].edad.toString()


        holder.btnDeletePlaneta.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val activity = it.context as AppCompatActivity
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete")
            builder.setMessage("Estas seguro de eliminar este Planeta?")
            builder.setPositiveButton("Yes"){ dialogInterface, i: Int ->
                val deleteItem = db.collection("Planeta").document(item.id)
                println(item.id)
                db.runBatch {batch ->
                    batch.delete(deleteItem)
                }.addOnCompleteListener {
                    Toast.makeText(activity, "Eliminacion Existosa", Toast.LENGTH_SHORT).show()
                    planetaList.removeAt(position)
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