package com.example.teacherapp.ViewModel.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teacherapp.ViewModel.Subjects_Handler

class Subjects_Factory (private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(Subjects_Handler::class.java)) {
                return Subjects_Handler(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}