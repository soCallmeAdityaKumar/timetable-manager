package com.example.timetablemanager.futuretask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.R
import com.example.timetablemanager.Roomdatabase.RoomEntity

class FutureTaskRecyclerViewAdapter: RecyclerView.Adapter<FutureTaskRecyclerViewAdapter.FutureTaskViewHolder>() {

private var taskList= emptyList<RoomEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureTaskViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.futuretaskitem_view,parent,false)
        return FutureTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder:FutureTaskViewHolder, position: Int) {
        val currentitem=taskList[position]
        holder.FutureTaskItemViewTaskName.text= currentitem.taskName
        holder.FutureTaskItemViewTaskDescription.text= currentitem.taskDescription
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
    class FutureTaskViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val FutureTaskItemViewTaskName=itemView.findViewById<TextView>(R.id.FutureTaskItemViewTaskName)
        val FutureTaskItemViewTaskDescription=itemView.findViewById<TextView>(R.id.FutureTaskItemViewTaskDescription)

    }
    fun setTask(roomEntity:List<RoomEntity>){
        this.taskList=roomEntity

        notifyDataSetChanged()
    }

}