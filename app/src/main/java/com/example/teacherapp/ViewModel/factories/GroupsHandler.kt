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
    init {
        operatorDAO=HelperDatabase.getInstance(application).operatorDao
        groups = operatorDAO.GetGroups()
        group = Groups(0,"", "")
    }

    fun AddGroup(groups: Groups) {
        viewModelScope.launch(Dispatchers.IO) {
            operatorDAO.InsertGroup(group)
        }
    }
    fun DeleteGroup(groups: Groups){
        viewModelScope.launch(Dispatchers.IO){
            operatorDAO.DeleteGroup(group)
        }
    }
}