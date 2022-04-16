package com.example.timetablemanager.futuretask

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.R
import com.example.timetablemanager.Roomdatabase.RoomEntity
import com.example.timetablemanager.Roomdatabase.ViewModel
import com.example.timetablemanager.Roomdatabase.updateTask


class FutureTaskRecyclerViewAdapter(val context: Context,val listener: ITaskRVAdapter): RecyclerView.Adapter<FutureTaskRecyclerViewAdapter.FutureTaskViewHolder>() {
     var taskList = ArrayList<RoomEntity>()
    private lateinit var TaskViewModel: ViewModel
   inner class FutureTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var FutureTaskItemViewTaskName: TextView = itemView.findViewById(R.id.FutureTaskItemViewTaskName)
        var FutureTaskItemViewTaskDescription: TextView = itemView.findViewById(R.id.FutureTaskItemViewTaskDescription)
       var cardview=itemView.findViewById<CardView>(R.id.cardView)
       var Image:ImageView=itemView.findViewById(R.id.delete)
   }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureTaskViewHolder {
        val vi = FutureTaskViewHolder(LayoutInflater.from(context).inflate(R.layout.futuretaskitem_view, parent, false))







        return vi
    }


    override fun onBindViewHolder(holder: FutureTaskViewHolder, position: Int) {
        val currentitem = taskList[position]
        holder.FutureTaskItemViewTaskName.text = currentitem.taskName
        holder.FutureTaskItemViewTaskDescription.text = currentitem.taskDescription
        holder.Image.setOnClickListener {

            val popupMenu=PopupMenu(context,it)
            popupMenu.setOnMenuItemClickListener { view->
                when(view.itemId){
                    R.id.update->{

                        val Intent=Intent(context,updateTask::class.java)
                        Intent.putExtra("id",taskList.get(holder.absoluteAdapterPosition).id)
                        context.startActivity(Intent)
                        true
                    }
                    R.id.delete->
                    {
                        AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Do you want to delete the message")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                listener.onItemClicked(taskList[holder.adapterPosition])
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }.create()
                            .show()

                        true
                    }
                    else->
                        false
                }

            }
            popupMenu.inflate(R.menu.updatedeletemenu)
            popupMenu.show()
        }



    }

    fun updatetaskList(taskListSource: List<RoomEntity>) {
        this.taskList = taskListSource as ArrayList<RoomEntity>
    }








    override fun getItemCount(): Int {
        return taskList.size
    }




        fun setTask(roomEntity:ArrayList<RoomEntity>){
    taskList.clear()
    taskList.addAll(roomEntity)

    notifyDataSetChanged()
}
    interface ITaskRVAdapter{
        fun onItemClicked(roomEntity: RoomEntity)
    }



    }













