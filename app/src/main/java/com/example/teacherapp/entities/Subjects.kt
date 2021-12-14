package com.example.teacherapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects_table")
data class Subjects (
@PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "id")
var classID: Long = 0L,
@ColumnInfo(name="name")
var className: String
)