package com.example.teacherapp.ViewModel.factories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherapp.database.HelperDatabase
import com.example.teacherapp.database.Operator_DAO
import com.example.teacherapp.entities.Grades
import com.example.teacherapp.entities.Students
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradesHandler(application: Application): AndroidViewModel(application) {
    private val operatorDao: Operator_DAO
    var gradeValue:String? = ""
    var whyGrade:String? = ""
    var grade: Grades
    val grades: LiveData<List<Grades>>
    init{
        operatorDao= HelperDatabase.getInstance(application).operatorDao
        grades = operatorDao.GetAllGrades()
        grade = Grades(0,0,"","")
    }
    fun AddGrade(grades: Grades) {
        viewModelScope.launch(Dispatchers.IO) {
            operatorDao.InsertGrade(grades)
        }
    }
    fun DeleteGrade(grades: Grades){
        viewModelScope.launch(Dispatchers.IO){
            operatorDao.DeleteGrade(grades)
        }
    }
}