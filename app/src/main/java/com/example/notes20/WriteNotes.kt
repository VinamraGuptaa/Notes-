package com.example.notes20

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_write_notes.*
import java.text.SimpleDateFormat
import java.util.*



class WriteNotes : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_notes)
        setSupportActionBar(toolbar_writenotes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_writenotes.setNavigationOnClickListener {
            onBackPressed()
     }



        val date = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd MMM h:mm a", Locale.getDefault())
        val mydate = sdf.format(date.time)
        tv_date.setText(mydate).toString()
        img_Save.setOnClickListener {
            if (et_Title.text.isNullOrBlank() || et_description.text.isNullOrBlank()) {         //The user writes and saves notes.
                Toast.makeText(
                    applicationContext,
                    "The title and body cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (et_description.length() > 500||et_Title.length()>15) {
                Toast.makeText(
                    applicationContext,
                    "Oops That's out of word limit..",
                    Toast.LENGTH_SHORT
                ).show()


            } else {

                val db = Room.databaseBuilder(applicationContext, Database::class.java, "Notes")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                val mynotes = Model()
                mynotes.Title = et_Title.text.toString()
                mynotes.Body = et_description.text.toString()
                mynotes.Date = tv_date.text.toString()
                db.callDao().savenote_dao(mynotes)
                Toast.makeText(this, "The note is Saved", Toast.LENGTH_SHORT).show()
                if(getcountdb().isNotEmpty()){
                    setResult(Activity.RESULT_OK)
                    finish()

                }


            }


            }













        }
    fun getcountdb(): List<Model> {
        val db = Room.databaseBuilder(applicationContext, Database::class.java, "Notes")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val list: List<Model>
        list = db.callDao().getnote()


        return list


    }


}















