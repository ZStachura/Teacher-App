package com.example.teacherapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students_table")
data class Students (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var studentID: Long = 0L,
    @ColumnInfo(name="album")
    var album: String,
    @ColumnInfo(name="name")
    var studentName: String,
    @ColumnInfo(name="surname")
    var studentsSurname: String,
    @ColumnInfo(name = "groupId")
    var group_id: Int)