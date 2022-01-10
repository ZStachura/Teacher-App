package com.example.teacherapp.ViewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.R
import com.example.teacherapp.ViewModel.factories.StudentsHandler
import com.example.teacherapp.entities.Subjects
import com.example.teacherapp.ViewModel.factories.SubjectsHandler
import com.example.teacherapp.entities.Students

class StudentsAdapter(private val students: LiveData<List<Students>>, private val viewModel: StudentsHandler): RecyclerView.Adapter<StudentsAdapter.StudentsHolder>() {
    inner class StudentsHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val textViewName = view.findViewById<TextView>(R.id.student_name)
        val textViewSurname = view.findViewById<TextView>(R.id.student_surname)
        val textViewAlbum = view.findViewById<TextView>(R.id.albumview)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_one_student, parent, false)
        return StudentsHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsHolder, position: Int) {
        holder.textViewName.text = students.value?.get(position)?.studentName
        holder.textViewSurname.text=students.value?.get(position)?.studentsSurname
        holder.textViewAlbum.text=students.value?.get(position)?.album
        holder.myView.setOnClickListener(){
            viewModel.studentName = students.value?.get(position)?.studentName
            viewModel.studentSurname = students.value?.get(position)?.studentsSurname
            viewModel.album=students.value?.get(position)?.album
            viewModel.student = Students(students.value?.get(position)?.studentID!!, students.value?.get(position)?.album!! ,students.value?.get(position)?.studentName!!,students.value?.get(position)?.studentsSurname!!,students.value?.get(position)?.group_id!! )
            holder.myView.findNavController().navigate(R.id.action_onegroup_fragment_to_student_fragment)
        }
    }

    override fun getItemCount()=students.value?.size?:0
}