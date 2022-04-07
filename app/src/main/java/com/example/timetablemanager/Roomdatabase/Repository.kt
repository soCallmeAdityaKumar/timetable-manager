package com.example.timetablemanager.Roomdatabase

import androidx.lifecycle.LiveData

class Repository (private val dao: Dao){
    val readAllTask:LiveData<List<RoomEntity>> = dao.readAllTask()

    suspend fun addTask(roomEntity: RoomEntity){
        dao.addTask(roomEntity)
    }
}