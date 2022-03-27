package com.example.timetablemanager.Postponedtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timetablemanager.R
import com.example.timetablemanager.futuretask.FutureTask
import com.example.timetablemanager.futuretask.FutureTaskRecyclerViewAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostponedTask.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostponedTask : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_postponed_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val PostponedTaskRecylerView=view.findViewById<RecyclerView>(R.id.PostponedTaskRecyclerView)
        PostponedTaskRecylerView?.layoutManager= LinearLayoutManager(activity)
        val items=fetchData()
        PostponedTaskRecylerView?.adapter= PostponedTaskRecyclerViewAdapter(items)


    }
    private fun fetchData():ArrayList<String>{
        val list=ArrayList<String>()
        for (i in 0 until 100){
            list.add("item $i")
        }
        return  list
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
        // TODO: Rename and change types and number of parameters
        fun newInstance() = PostponedTask()

    }
}
