package com.example.teacherapp.ViewModel.factories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherapp.database.HelperDatabase
import com.example.teacherapp.database.Operator_DAO
import com.example.teacherapp.entities.Groups
import com.example.teacherapp.entities.Subjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.Subject

class GroupsHandler(application: Application): AndroidViewModel(application) {
    private val operatorDAO : Operator_DAO
    var groupName:String? = ""
    var subjectNam:String?=""
    var group :Groups
    val groups: LiveData<List<Groups>>
    var currentgroups: LiveData<List<Groups>>
    var currentSubject: Subjects
    init {
        operatorDAO=HelperDatabase.getInstance(application).operatorDao
        groups = operatorDAO.GetGroups()
        currentgroups=groups
        currentSubject=Subjects(0L,"")
        group = Groups(0L,"", 0L)
    }

    fun AddGroup(group: Groups) {
        viewModelScope.launch(Dispatchers.IO) {
            operatorDAO.InsertGroup(group)
        }
    }
    fun DeleteGroup(group: Groups){
        viewModelScope.launch(Dispatchers.IO){
            operatorDAO.DeleteGroup(group)
        }
    }
    fun GetThatGroups(idsubject: Long):LiveData<List<Groups>>{
        currentgroups=operatorDAO.GetSubjectGroup(idsubject)
        return currentgroups
    }
}