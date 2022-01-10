package com.example.teacherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
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
    @Query("SELECT * FROM subjects_table ORDER BY subjects_table.name ASC")
    fun GetAllSubjects(): LiveData<List<Subjects>>

    @Insert
    fun InsertSubject(subject:Subjects)

    @Delete
    fun DeleteSubject(subject: Subjects)

    //GROUPS
    @Query("SELECT * FROM groups_table WHERE groups_table.subject_id = :subject ORDER BY groups_table.group_name ASC")
    fun GetSubjectGroup(subject: Long): LiveData<List<Groups>>

    @Query("SELECT * FROM groups_table")
    fun GetGroups(): LiveData<List<Groups>>

    @Insert
    fun InsertGroup(group: Groups)

    @Delete
    fun DeleteGroup(group: Groups)

    //STUDENTS
    @Query("SELECT * FROM students_table")
    fun GetAllStudents(): LiveData<List<Students>>

    @Query("SELECT * FROM students_table WHERE students_table.groupId = :group ORDER BY students_table.surname ASC")
    fun GetGroupStudent(group: Long): LiveData<List<Students>>

    @Query("SELECT * FROM students_table WHERE students_table.album != :idalbum AND students_table.groupId != :idgroup")
    fun GetNewStudent(idalbum: String, idgroup: Long): LiveData<List<Students>>

    @Query("SELECT EXISTS (SELECT * FROM students_table WHERE students_table.album != :idalbum)")
    fun SameStudent(idalbum: String): Boolean

    @Insert
    fun InsertStudent(student: Students)

    @Delete
    fun DeleteStudent(student: Students)


    //STUDENTINGROUP

    //GRADES

    @Query("SELECT * FROM grades_table")
    fun GetAllGrades(): LiveData<List<Grades>>

    @Query("SELECT * FROM grades_table WHERE grades_table.id_student = :student")
    fun GetGradeStudent(student:Long): LiveData<List<Grades>>

    @Insert
    fun InsertGrade(grades: Grades)

    @Delete
    fun DeleteGrade(grades: Grades)

}