package com.example.timetablemanager.futuretask

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.timetablemanager.*
import com.example.timetablemanager.Roomdatabase.RoomEntity
import com.example.timetablemanager.Roomdatabase.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton



/**
 * A simple [Fragment] subclass.
 * Use the [FutureTask.newInstance] factory method to
 * create an instance of this fragment.
 */
class FutureTask : Fragment() {

    private lateinit var viewModel: ViewModel

lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_future_task, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
 val roomEntity:RoomEntity

        val FutureTaskRecylerView=view.findViewById<RecyclerView>(R.id.FutureTaskRecyclerView)
        val adapter=FutureTaskRecyclerViewAdapter()
        FutureTaskRecylerView.adapter=adapter
        FutureTaskRecylerView.layoutManager=LinearLayoutManager(requireContext())


        viewModel=ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.readAllTask.observe(viewLifecycleOwner, Observer {roomEntity-> adapter.setTask(roomEntity) })


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



}