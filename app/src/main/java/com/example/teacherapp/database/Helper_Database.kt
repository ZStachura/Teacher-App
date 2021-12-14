package com.example.teacherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherapp.entities.Subjects

abstract class Helper_Database {
    @Database(entities=[Subjects::class], version = 1, exportSchema = false)
    abstract class HelperDatabase: RoomDatabase() {
        abstract val Helper_Database: Helper_Database

        companion object {
            @Volatile
            private var INSTANCE: HelperDatabase? = null
            fun getInstance(context: Context): HelperDatabase {
                synchronized(this) {
                    var instance = INSTANCE
                    if(instance == null ){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            HelperDatabase::class.java,
                            "Helper_database"
                        ).fallbackToDestructiveMigration().build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
    }
}