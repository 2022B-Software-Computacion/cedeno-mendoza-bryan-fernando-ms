package com.example.cloneshazam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LibrayAdapter(private val childList: List<ChildItemD>) :
    RecyclerView.Adapter<LibrayAdapter.ChildViewHolderLibrary>() {


    inner class ChildViewHolderLibrary(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.iv_child_library_logo)
        val title: TextView = itemView.findViewById(R.id.tv_childTitle_Library)
        val singer: TextView = itemView.findViewById(R.id.tv_child_Library_singer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolderLibrary {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.library_child, parent, false)
        return ChildViewHolderLibrary(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolderLibrary, position: Int) {




        holder.logo.setImageResource(childList[position].logo)
        holder.title.text = childList[position].title
        holder.singer.text= childList[position].singer
    }

    override fun getItemCount(): Int {
        return childList.size
    }

}