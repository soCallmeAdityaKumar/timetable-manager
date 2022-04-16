package com.example.timetablemanager.futuretask

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class FutureTaskItem(context: Context,
                          swipeDirs: Int
):ItemTouchHelper.SimpleCallback(ItemTouchHelper.END, swipeDirs) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }
}