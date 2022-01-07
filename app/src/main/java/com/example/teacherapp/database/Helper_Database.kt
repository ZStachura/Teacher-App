package com.example.teacherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherapp.entities.Subjects

@Database(entities=[Subjects::class], version = 1, exportSchema = true)
abstract class Helper_Database: RoomDatabase() {
    abstract val operatorDao: Operator_DAO

    companion object {
        @Volatile
        private var INSTANCE: Helper_Database? = null
        fun getInstance(context: Context): Helper_Database {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null ){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Helper_Database::class.java,
                        "Helper_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
                }
            }
        }
}