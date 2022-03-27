package com.example.timetablemanager.GoalStatus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.Postponedtask.PostponedTaskRecyclerViewAdapter
import com.example.timetablemanager.R

class GoalStatusRecyclerViewAdapter(private val items:ArrayList<String>): RecyclerView.Adapter<GoalStatusRecyclerViewAdapter.GoalStatusViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GoalStatusViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.goalstatusitem_view,parent,false)
        return GoalStatusViewHolder(view)
    }

    class GoalStatusViewHolder(itemView: View):RecyclerView.ViewHolder(itemView ) {
        val titleView=itemView.findViewById<TextView>(R.id.GoalStatusRecyclerViewTextView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GoalStatusViewHolder, position: Int) {
        val currentitem=items[position]
        holder.titleView.text=currentitem
    }
}