package com.example.teacherapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups_table")
data class Groups (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var groupID: Long = 0L,
    @ColumnInfo(name="group_name")
    var groupName: String,
    @ColumnInfo(name = "day")
    var day: String,
    @ColumnInfo(name = "start_hour")
    var start: String,
    @ColumnInfo(name = "end_hour")
    var end: String,
    @ColumnInfo(name="subject_id")
    var subjectID: Long = 0L )