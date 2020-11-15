

package com.example.notes20

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<FloatingActionButton>(R.id.fab)
        supportActionBar?.title = "Notes++"


        fab.setOnClickListener {

            val intent = Intent(this, WriteNotes::class.java)
            startActivityForResult(intent, writenotesReqCode)              //The Fab button takes you to write note.


        }
        callrecyclerview()                     //Calls and intialize recycler view with notes database.

        attachtorecyleview()                  //Attachs the Swipe Function to the recycle






    }
    fun attachtorecyleview(){
        val editTouchHelper=ItemTouchHelper(swipeEdit)
        editTouchHelper.attachToRecyclerView(rv_notes)
        val deletetouchhelper=ItemTouchHelper(swipeDelete)           // Calls the itemtouchhelper and attachs it to rv.
        deletetouchhelper.attachToRecyclerView(rv_notes)


    }

    fun getnotesfromdatabase(): List<Model> {
        val db = Room.databaseBuilder(applicationContext, Database::class.java, "Notes")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val list: List<Model>
        list = db.callDao().getnote()                                        //Gets the list of notes from the database.

        return list


    }

    fun callrecyclerview() {
        if (getnotesfromdatabase().size > 0) {
            iv_nonotes.visibility = View.GONE
            tv_nonotesavailble.visibility = View.GONE
            rv_notes.visibility = View.VISIBLE
            rv_notes.layoutManager = LinearLayoutManager(this.baseContext)
            rv_notes.adapter = Notes_Adapter(getnotesfromdatabase(), this)


        } else {
            iv_nonotes.visibility = View.VISIBLE
            tv_nonotesavailble.visibility = View.VISIBLE
            rv_notes.visibility = View.GONE

        }


    }

    val swipeEdit = object : SwipetoEdit(this, 0, ItemTouchHelper.RIGHT) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position=viewHolder.adapterPosition
            val adapter=rv_notes.adapter as Notes_Adapter
            adapter.notifyedititem(this@MainActivity,position, updateNoteCode)





       }
    }
    val swipeDelete=object :SwipetoDelete(this,1,ItemTouchHelper.LEFT){
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            super.onSwiped(viewHolder, direction)
            val position=viewHolder.adapterPosition
            val adapter=rv_notes.adapter as Notes_Adapter
            val notetodelete=adapter.getposition(position)
            val id=notetodelete.id
            val db = Room.databaseBuilder(applicationContext, Database::class.java, "Notes")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            db.callDao().deletenote(id)
            adapter.notifyItemRemoved(position)
            Toast.makeText(this@MainActivity,"The note is Deleted",Toast.LENGTH_SHORT).show()
            callrecyclerview()


        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == writenotesReqCode) {
            if (resultCode == Activity.RESULT_OK) {
                callrecyclerview()
            }
        }
        else if (requestCode== updateNoteCode){
            if(resultCode==Activity.RESULT_OK){
                callrecyclerview()
            }
        }
        else {
            Log.e("Activity", "Cancelled or Back pressed")
        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
           R.id.Dev_info->{
               Toast.makeText(this@MainActivity,"Created by Vinamra Gupta ;)",Toast.LENGTH_LONG).show()


               true
           }


            else -> super.onOptionsItemSelected(item)
        }


    }



    companion object {
        const val writenotesReqCode: Int = 1
         const val mainactivitycode: Int = 2
        const val updateNoteCode:Int=3
        val writenotescode:String="Update"
        private const val storage_permissioncode = 1
        val reqCode: String = "EditItem"

}








}