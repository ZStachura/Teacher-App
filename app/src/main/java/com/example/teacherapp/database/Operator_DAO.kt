package com.example.teacherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.teacherapp.entities.Subjects
import com.example.teacherapp.entities.Students
import com.example.teacherapp.entities.Groups
import com.example.teacherapp.entities.Grades
import com.example.teacherapp.entities.List_in_group

@Dao
interface Operator_DAO {

    //Subjects
    @Query("SELECT * FROM subjects_table")
    fun GetAllSubjects(): LiveData<List<Subjects>>

    @Insert
    fun InsertSubject(subjects:Subjects)

    @Delete
    fun DeleteSubject(subjects: Subjects)
}