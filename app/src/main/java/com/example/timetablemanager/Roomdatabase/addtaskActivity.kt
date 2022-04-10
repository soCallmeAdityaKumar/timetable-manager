package com.example.timetablemanager.Roomdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.timetablemanager.MainActivity
import com.example.timetablemanager.R

class addtaskActivity:AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_task)

        viewModel=ViewModelProvider(this).get(ViewModel::class.java)

        val addTaskbutton = findViewById<Button>(R.id.addTaskbutton)
        addTaskbutton.setOnClickListener {

            insertTaskToDatabase()
        }
    }

    private fun insertTaskToDatabase() {

        val AddTaskEditText=findViewById<EditText>(R.id.AddTaskEditText).text.toString()
        val AddDescriptionEditText=findViewById<EditText>(R.id.AddDescriptionEditText).text.toString()

        if(inputCheck(AddTaskEditText,AddDescriptionEditText)){

            val roomEntity=RoomEntity(0,AddTaskEditText,AddDescriptionEditText)
            viewModel.addTask(roomEntity)
            Toast.makeText(this, "Successfully Added the Task",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
            }
        else{
            Toast.makeText(this,"Please Fill All the Data",Toast.LENGTH_LONG).show()
        }

    }
    private fun inputCheck(AddTaskEditText: String, AddDescription: String): Boolean {
        return !(AddTaskEditText.isEmpty()&& AddDescription.isEmpty())



    }
}