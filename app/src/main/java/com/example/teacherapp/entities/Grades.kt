package com.example.teacherapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades_table")
data class Grades (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gradeID: Long = 0L,
    @ColumnInfo(name="id_student")
    var student_id: Int,
    @ColumnInfo(name="grade")
    var grade: String,
    @ColumnInfo(name="why_grade")
    var why_grade: String)