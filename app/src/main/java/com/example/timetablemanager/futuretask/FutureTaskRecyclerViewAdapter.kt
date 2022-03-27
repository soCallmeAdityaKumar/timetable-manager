package com.example.timetablemanager.futuretask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.R

class FutureTaskRecyclerViewAdapter(private val items:ArrayList<String>): RecyclerView.Adapter<FutureTaskRecyclerViewAdapter.FutureTaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureTaskViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.futuretaskitem_view,parent,false)
        return FutureTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder:FutureTaskViewHolder, position: Int) {
        val currentitem=items[position]
        holder.titleView.text=currentitem
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class FutureTaskViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val titleView=itemView.findViewById<TextView>(R.id.FutureTaskRecyclerViewTextView)
    }

}