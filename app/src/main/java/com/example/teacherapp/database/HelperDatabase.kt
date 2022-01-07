package com.example.teacherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherapp.entities.Subjects
import com.example.teacherapp.entities.Groups
import com.example.teacherapp.entities.Students
import com.example.teacherapp.entities.Grades
import com.example.teacherapp.entities.List_in_group

@Database(entities=[Subjects::class, Groups::class, Students::class, Grades::class, List_in_group::class], version = 1, exportSchema = true)
abstract class HelperDatabase: RoomDatabase() {
    abstract val operatorDao: Operator_DAO

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