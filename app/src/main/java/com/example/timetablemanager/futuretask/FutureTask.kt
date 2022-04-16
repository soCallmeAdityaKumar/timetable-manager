package com.example.timetablemanager.futuretask

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.*
import com.example.timetablemanager.Roomdatabase.RoomEntity
import com.example.timetablemanager.Roomdatabase.ViewModel
import com.example.timetablemanager.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [FutureTask.newInstance] factory method to
 * create an instance of this fragment.
 */
class FutureTask : Fragment() ,FutureTaskRecyclerViewAdapter.ITaskRVAdapter {


    private lateinit var viewModel: ViewModel


lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_future_task, container, false)

        return view
    }

//



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val roomEntity:RoomEntity
        val FutureTaskRecylerView=view.findViewById<RecyclerView>(R.id.FutureTaskRecyclerView)
        val adapter=FutureTaskRecyclerViewAdapter(view.context,this)

        FutureTaskRecylerView.adapter=adapter
        FutureTaskRecylerView.layoutManager=LinearLayoutManager(requireContext())


        viewModel=ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.readAllTask.observe(viewLifecycleOwner, Observer {roomEntity-> adapter.setTask(
            roomEntity as ArrayList<RoomEntity>
        ) })



    }






    companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostponedTask.
     */

    fun newInstance() = FutureTask()

}

    override fun onItemClicked(roomEntity: RoomEntity) {
        viewModel.deletetask(roomEntity)
    }



}