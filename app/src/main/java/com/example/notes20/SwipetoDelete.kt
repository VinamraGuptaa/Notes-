package com.example.notes20

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
@SuppressWarnings
abstract class SwipetoDelete(context:Context,dragDir:Int,swipeDir:Int):ItemTouchHelper.SimpleCallback(dragDir,swipeDir) {
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