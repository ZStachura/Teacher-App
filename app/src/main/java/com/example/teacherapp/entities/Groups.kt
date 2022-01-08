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
    @ColumnInfo(name="subject_name")
    var subjectNam: String? )