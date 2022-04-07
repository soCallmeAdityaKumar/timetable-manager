package com.example.timetablemanager.Roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val taskName:String,
    val taskDescription:String
)
