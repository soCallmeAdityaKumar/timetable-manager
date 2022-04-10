package com.example.timetablemanager.futuretask

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.R
import com.example.timetablemanager.Roomdatabase.RoomEntity
import com.example.timetablemanager.Roomdatabase.ViewModel
import com.example.timetablemanager.Roomdatabase.updateTask


class FutureTaskRecyclerViewAdapter(val context: Context, val listener: FutureTask): RecyclerView.Adapter<FutureTaskRecyclerViewAdapter.FutureTaskViewHolder>() {
     var taskList = ArrayList<RoomEntity>()
    private lateinit var TaskViewModel: ViewModel
   inner class FutureTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var FutureTaskItemViewTaskName: TextView = itemView.findViewById(R.id.FutureTaskItemViewTaskName)
        var FutureTaskItemViewTaskDescription: TextView = itemView.findViewById(R.id.FutureTaskItemViewTaskDescription)
        var MenuImage: ImageView = itemView.findViewById(R.id.Menu)


    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureTaskViewHolder {
        val view = FutureTaskViewHolder(LayoutInflater.from(context).inflate(R.layout.futuretaskitem_view, parent, false))
        return view
    }

    override fun onBindViewHolder(holder: FutureTaskViewHolder, position: Int) {
        val currentitem = taskList[position]
        holder.FutureTaskItemViewTaskName.text = currentitem.taskName
        holder.FutureTaskItemViewTaskDescription.text = currentitem.taskDescription
        holder.MenuImage.setOnClickListener {
            context.startActivity(Intent(context,updateTask::class.java))
        }

    }
    override fun getItemCount(): Int {
        return taskList.size
    }

fun setTask(roomEntity:ArrayList<RoomEntity>){
    taskList.clear()
    taskList.addAll(roomEntity)
    notifyDataSetChanged()
}

    }












