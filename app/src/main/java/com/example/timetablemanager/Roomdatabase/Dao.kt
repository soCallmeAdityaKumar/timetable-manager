package com.example.timetablemanager.Roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(roomEntity: RoomEntity)

    @Delete
    suspend fun deletetask(roomEntity: RoomEntity)

    @Query("DELETE FROM TASKS")
    suspend fun deleteAll()

    @Update
    suspend fun updateTask(roomEntity: RoomEntity)

//    @Query("SELECT id From Tasks " )
//    suspend fun getId(roomEntity: RoomEntity)

    @Query("SELECT *FROM Tasks ORDER BY id ASC")
    fun readAllTask():LiveData<List<RoomEntity>>

}