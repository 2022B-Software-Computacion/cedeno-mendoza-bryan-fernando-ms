package com.example.cloneshazam

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentAdapter(private val parentList: List<ParentItemD>) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {
    private val VIEW_TYPE_FIRST_ITEM = 0
    private val VIEW_TYPE_NORMAL_ITEM = 1


    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.tv_parentTitle)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.langRecyclerView)
        val seeallButton = itemView.findViewById<Button>(R.id.btn_seeAll)
        val constLayout = itemView.findViewById<ConstraintLayout>(R.id.ct_layaut)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_FIRST_ITEM
        } else {
            VIEW_TYPE_NORMAL_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(R.layout.chartsparent, parent, false)
        return ParentViewHolder(view)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        if (position==0){
            holder.titleTv.visibility=View.GONE
            holder.seeallButton.visibility=View.GONE

            holder.constLayout.apply {
                // setear el nuevo foreground del botón
                foreground = ContextCompat.getDrawable(context, R.drawable.mapa)


            }
            holder.childRecyclerView.setHasFixedSize(true)
        }else{
            val parentItem = parentList[position]
            holder.titleTv.text = parentItem.title
            holder.childRecyclerView.setHasFixedSize(true)
            holder.childRecyclerView.layoutManager = GridLayoutManager(holder.itemView.context, 3)
            val adapter = ChildAdapter(parentItem.mList)
            holder.childRecyclerView.adapter = adapter
        }

    }

    override fun getItemCount(): Int {
        return parentList.size
    }
}