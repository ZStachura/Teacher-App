package com.example.teacherapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades_table")
data class Grades (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gradeID: Long = 0L,
    @ColumnInfo(name="id_st_in_gr")
    var st_in_gr_id: Long = 0L,
    @ColumnInfo(name="grade")
    var grade: Int,
    @ColumnInfo(name="why_grade")
    var why_grade: String)