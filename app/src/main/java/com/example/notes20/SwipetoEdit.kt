package com.example.notes20

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
@SuppressWarnings
abstract class SwipetoEdit(context: Context,dragDir:Int,SwipeDir:Int):ItemTouchHelper.SimpleCallback(dragDir,SwipeDir) {
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