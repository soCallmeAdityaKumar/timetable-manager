package com.example.timetablemanager.Roomdatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Tasks")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var taskName:String,
    var taskDescription:String
):Parcelable
