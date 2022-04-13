package com.example.timetablemanager.Roomdatabase

import androidx.lifecycle.LiveData

class Repository (private val dao: Dao){
    val readAllTask:LiveData<List<RoomEntity>> = dao.readAllTask()

    suspend fun addTask(roomEntity: RoomEntity){
        dao.addTask(roomEntity)
    }
    suspend fun deletetask(roomEntity: RoomEntity){
        dao.deletetask(roomEntity)
    }
    suspend fun deleteAlltask(){
        dao.deleteAll()
    }

    suspend fun getId(roomEntity: RoomEntity){
        dao.getId(roomEntity)
    }
    suspend fun updateTask(roomEntity: RoomEntity){
        dao.updateTask(roomEntity)
    }

}