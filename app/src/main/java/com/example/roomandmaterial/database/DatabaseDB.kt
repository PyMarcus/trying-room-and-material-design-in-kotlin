package com.example.roomandmaterial.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomandmaterial.models.UserEntity
import com.example.roomandmaterial.repository.UserDAO

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class DatabaseDB(): RoomDatabase(){

    abstract fun userDAO(): UserDAO

    companion object{
        private lateinit var INSTANCE: DatabaseDB

        fun getDatabase(context: Context): DatabaseDB{  // singleton
            if(!::INSTANCE.isInitialized){
                synchronized(DatabaseDB::class){ // sql does not allow multithreads
                    INSTANCE = Room
                        .databaseBuilder(context, DatabaseDB::class.java, "database_db")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

}