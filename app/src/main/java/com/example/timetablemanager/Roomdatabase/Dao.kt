package com.example.timetablemanager.Roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(roomEntity: RoomEntity)

    @Query("SELECT *FROM Tasks ORDER BY id ASC")
    fun readAllTask():LiveData<List<RoomEntity>>

}