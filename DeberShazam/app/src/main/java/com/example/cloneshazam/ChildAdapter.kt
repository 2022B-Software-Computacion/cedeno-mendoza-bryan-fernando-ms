package com.example.cloneshazam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildAdapter(private val childList: List<ChildItemD>) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.iv_child_logo)
        val title: TextView = itemView.findViewById(R.id.tv_childTitle)
        val singer: TextView = itemView.findViewById(R.id.tv_child_singer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.charts_child, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.logo.setImageResource(childList[position].logo)
        holder.title.text = childList[position].title
        holder.singer.text= childList[position].singer
    }

    override fun getItemCount(): Int {
        return childList.size
    }

}