package com.example.timetablemanager.Roomdatabase

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.timetablemanager.MainActivity
import com.example.timetablemanager.R
import com.example.timetablemanager.databinding.ActivityMainBinding
import com.example.timetablemanager.databinding.FragmentAddTaskBinding
import com.example.timetablemanager.notification.AlarmReceiver
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class addtaskActivity:AppCompatActivity(){
private lateinit var binding: FragmentAddTaskBinding
    private lateinit var timePicker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding= FragmentAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)






        viewModel = ViewModelProvider(this).get(ViewModel::class.java)


        binding.addTaskbutton.setOnClickListener {

            insertTaskToDatabase()
            createNotificationChannel()
        }
        binding.SelectAlarmButton.setOnClickListener {

            showTimePicker()
        }

        binding.SetAlarmButton.setOnClickListener {

            setAlarm()
            createNotificationChannel()


        }
    }

    private fun setAlarm() {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        calendar= Calendar.getInstance()
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Toast.makeText(this, "Alarm was set successfully", Toast.LENGTH_LONG).show()


    }

    private fun insertTaskToDatabase() {

        val AddTaskEditText = findViewById<EditText>(R.id.AddTaskEditText).text.toString()
        val AddDescriptionEditText =
            findViewById<EditText>(R.id.AddDescriptionEditText).text.toString()

        if (inputCheck(AddTaskEditText, AddDescriptionEditText)) {

            val roomEntity = RoomEntity(0, AddTaskEditText, AddDescriptionEditText)
            viewModel.addTask(roomEntity)


            val NewtaskNotification= NotificationCompat.Builder(this,"TimeTable Manager")
                .setSmallIcon(R.drawable.ic_lau_background)
                .setContentTitle("TimeSand")
                .setContentText("New Task Added")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()


            val NotificationManager= NotificationManagerCompat.from(this)
            NotificationManager.notify(1234,NewtaskNotification)

            Toast.makeText(this, "Successfully Added the Task", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))

        } else {
            Toast.makeText(this, "Please Fill All the Data", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(AddTaskEditText: String, AddDescription: String): Boolean {
        return !(AddTaskEditText.isEmpty() && AddDescription.isEmpty())


    }

    private fun showTimePicker() {
        timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()
        timePicker.show(supportFragmentManager, "TimeTable Manager")
        timePicker.addOnPositiveButtonClickListener {
            val selectedTime = findViewById<TextView>(R.id.selectedTime)
            if (timePicker.hour > 12) {
                selectedTime.text =
                    String.format("%02d", timePicker.hour - 12) + ":" + String.format(
                        "%02d", timePicker.minute
                    ) + "PM"
            } else {

                selectedTime.text = String.format("%02d", timePicker.hour) + ":" + String.format(
                    "%02d", timePicker.minute
                ) + "AM"
            }



            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = timePicker.hour
            calendar[Calendar.MINUTE] = timePicker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0
        }


    }
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "NotificationReceiverChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("TimeTable Manager", name, importance)
            channel.description = description
            val NotificationManager =
                getSystemService(NotificationManager::class.java)
            NotificationManager?.createNotificationChannel(channel)


        }
    }
}