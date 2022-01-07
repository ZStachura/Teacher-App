package com.example.teacherapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_in_group_table")
data class List_in_group (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_st_w_gr")
    var student_group_ID: Long = 0L,
    @ColumnInfo(name = "id_student")
    var student_ID: Long = 0L,
    @ColumnInfo(name="id_group")
    var groupID: Long = 0L,
    @ColumnInfo(name="id_subject")
    var subjectID: Long = 0L )