package com.example.timetablemanager.Roomdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.timetablemanager.MainActivity
import com.example.timetablemanager.R

class updateTask:AppCompatActivity() {
    private lateinit var viewModel: ViewModel
    private lateinit var Database:Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.updatetaskactivity)

        viewModel= ViewModelProvider(this).get(ViewModel::class.java)

        val addTaskbutton = findViewById<Button>(R.id.updateTaskbutton)
        addTaskbutton.setOnClickListener {

            updateTaskToDatabase()
        }
    }

    private fun updateTaskToDatabase() {

        var UpdateTaskName=findViewById<EditText>(R.id.UpdateTaskNameEditText).text.toString()
        var UpdateDescription=findViewById<EditText>(R.id.UpdateDescriptionEditText).text.toString()
        val id=intent.getIntExtra("id",0)

        if(inputCheck(UpdateTaskName,UpdateDescription)){

            var roomEntity=RoomEntity(id,UpdateTaskName,UpdateDescription)
            viewModel.updateTask(roomEntity)
            Toast.makeText(this, "Successfully updated the Task", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            val NewtaskNotification= NotificationCompat.Builder(this,"TimeTable Manager")
                .setSmallIcon(R.drawable.ic_lau_background)
                .setContentTitle("TimeSand")
                .setContentText("Task Updated")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()


            val NotificationManager= NotificationManagerCompat.from(this)
            NotificationManager.notify(1234,NewtaskNotification)
        }
        else{
            Toast.makeText(this,"Please Fill All the Data", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(AddTaskEditText: String, AddDescription: String): Boolean {
        return !(AddTaskEditText.isEmpty()&& AddDescription.isEmpty())



    }
}