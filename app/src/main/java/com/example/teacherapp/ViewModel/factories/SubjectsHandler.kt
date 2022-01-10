package com.example.teacherapp.ViewModel.factories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherapp.database.Operator_DAO
import com.example.teacherapp.database.HelperDatabase
import com.example.teacherapp.entities.Subjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectsHandler(application: Application):AndroidViewModel(application) {
    private val operatorDao: Operator_DAO
    var subjectName:String? = ""
    var subject:Subjects
    val subjects: LiveData<List<Subjects>>
    val currentSubject: LiveData<List<Subjects>>
    init{
        operatorDao= HelperDatabase.getInstance(application).operatorDao
        subjects = operatorDao.GetAllSubjects()
        currentSubject = subjects
        subject = Subjects(0,"")
    }
    fun AddSubject(subject: Subjects) {
        viewModelScope.launch(Dispatchers.IO) {
            operatorDao.InsertSubject(subject)
        }
    }
    fun DeleteSubject(subject: Subjects){
        viewModelScope.launch(Dispatchers.IO){
            operatorDao.DeleteSubject(subject)
        }
    }
}