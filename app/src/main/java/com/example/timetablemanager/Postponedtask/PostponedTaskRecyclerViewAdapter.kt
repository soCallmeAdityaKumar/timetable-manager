package com.example.timetablemanager.Postponedtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.R
import com.example.timetablemanager.futuretask.FutureTaskRecyclerViewAdapter

class PostponedTaskRecyclerViewAdapter(private val items:ArrayList<String>): RecyclerView.Adapter<PostponedTaskRecyclerViewAdapter.PostponedTaskViewHolder>() {
    class PostponedTaskViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val titleView=itemView.findViewById<TextView>(R.id.PostponedTaskRecyclerViewTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostponedTaskViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.postponedtaskitem_view,parent,false)
        return PostponedTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostponedTaskViewHolder, position: Int) {
        val currentitem=items[position]
        holder.titleView.text=currentitem
    }

    override fun getItemCount(): Int {
        return items.size
    }
}