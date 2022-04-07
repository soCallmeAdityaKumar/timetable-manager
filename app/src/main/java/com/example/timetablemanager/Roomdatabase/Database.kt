package com.example.timetablemanager.Roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [RoomEntity::class],version = 1,exportSchema = false)
abstract class Database : RoomDatabase(){


    abstract fun dao():Dao
    companion object{
        @Volatile
        private var INSTANCE:com.example.timetablemanager.Roomdatabase.Database?=null




        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context):com.example.timetablemanager.Roomdatabase.Database{

            val instance= INSTANCE
            if(instance!=null){
                return instance
            }
            synchronized(this){
                val instance =Room.databaseBuilder(
                    context.applicationContext,
                    com.example.timetablemanager.Roomdatabase.Database::class.java,"database"
                ).build()
                INSTANCE=instance
                return instance
            }



        }

    }
}