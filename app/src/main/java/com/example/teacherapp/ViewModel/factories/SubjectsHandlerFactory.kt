package com.example.teacherapp.ViewModel.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import com.example.teacherapp.database.HelperDatabase

class SubjectsHandlerFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubjectsHandler::class.java)){
            return SubjectsHandler(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}