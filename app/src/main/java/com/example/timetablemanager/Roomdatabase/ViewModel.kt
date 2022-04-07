package com.example.timetablemanager.Roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application):AndroidViewModel(application) {

     val readAllTask:LiveData<List<RoomEntity>>

    private val repository:Repository

    init {
        val dao=Database.getDatabase(application).dao()
        repository= Repository(dao)
        readAllTask=repository.readAllTask
    }
    fun addTask(roomEntity: RoomEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTask(roomEntity)
        }
    }
}