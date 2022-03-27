package com.example.timetablemanager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.timetablemanager.GoalStatus.GoalStatus
import com.example.timetablemanager.Postponedtask.PostponedTask
import com.example.timetablemanager.futuretask.FutureTask
import java.util.concurrent.locks.Condition

class FragmentViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{FutureTask()}
            1->{PostponedTask()}
            2->{GoalStatus()}
            else->{FutureTask()}
        }
    }
}

