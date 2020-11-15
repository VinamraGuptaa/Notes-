package com.example.notes20

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shownotes.view.*

class Notes_Adapter(val items:List<Model>,val context:Context):RecyclerView.Adapter<Notes_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.shownotes, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notes = items.get(position)
        holder.rvView.tv_Title.text = notes.Title
        holder.rvView.tv_showdate.text = notes.Date
        holder.rvView.tv_body.text = notes.Body


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvView = view.rvView


    }

    fun notifyedititem(activity: Activity, position: Int, RequestCode: Int) {
        val intent = Intent(context, updateActivity::class.java)
        intent.putExtra(MainActivity.reqCode, items[position])
        activity.startActivityForResult(intent, RequestCode)
        notifyItemChanged(position)


    }

    fun notifychange() {
        notifyDataSetChanged()
    }

    fun getposition(position: Int): Model {
        return items.get(position)
    }




}




















