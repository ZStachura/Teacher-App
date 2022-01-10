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
    val students: LiveData<List<Students>>
    init{
        operatorDao= HelperDatabase.getInstance(application).operatorDao
        students = operatorDao.GetAllStudents()
        currentstudents=students
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
        currentstudents=operatorDao.GetGroupStudent(idgroup)
        return currentstudents
    }
}