package com.example.teacherapp.ViewModel.factories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherapp.database.Operator_DAO
import com.example.teacherapp.database.HelperDatabase
import com.example.teacherapp.entities.Groups
import com.example.teacherapp.entities.Students
import com.example.teacherapp.entities.Subjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsHandler(application: Application):AndroidViewModel(application) {
    private val operatorDao: Operator_DAO
    var studentName:String? = ""
    var studentSurname:String? = ""
    var album:String?=""
    var student: Students
    var currentstudents: LiveData<List<Students>>
    var newstudents: LiveData<List<Students>>
    var samestudent: Boolean
    val students: LiveData<List<Students>>
    init{
        operatorDao= HelperDatabase.getInstance(application).operatorDao
        students = operatorDao.GetAllStudents()
        currentstudents=students
        newstudents=students
        samestudent=false
        student = Students(0,"","","",0)
    }
    fun AddStudent(student: Students) {
        viewModelScope.launch(Dispatchers.IO) {
            operatorDao.InsertStudent(student)
        }
    }
    fun DeleteStudent(student: Students){
        viewModelScope.launch(Dispatchers.IO){
            operatorDao.DeleteStudent(student)
        }
    }
    fun GetThatStudents(idgroup: Long):LiveData<List<Students>>{
        viewModelScope.launch(Dispatchers.IO){
        currentstudents=operatorDao.GetGroupStudent(idgroup)}
        return currentstudents
    }

    fun GetNewStudents(idalbum: String, idgroup: Long):LiveData<List<Students>>{
        viewModelScope.launch(Dispatchers.IO){
        newstudents=operatorDao.GetNewStudent(idalbum,idgroup)}
        return newstudents
    }

    fun IsStudent(idalbum: String):Boolean{
        viewModelScope.launch(Dispatchers.IO){
        samestudent=operatorDao.SameStudent(idalbum)}
        return samestudent
    }
}