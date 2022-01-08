package com.example.teacherapp.ViewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.R
import com.example.teacherapp.ViewModel.factories.GradesHandler
import com.example.teacherapp.ViewModel.factories.StudentsHandler
import com.example.teacherapp.entities.Subjects
import com.example.teacherapp.ViewModel.factories.SubjectsHandler
import com.example.teacherapp.entities.Grades
import com.example.teacherapp.entities.Students

class GradesAdapter(private val grades: LiveData<List<Grades>>, private val viewModel: GradesHandler): RecyclerView.Adapter<GradesAdapter.GradesHolder>() {
    inner class GradesHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val textViewGrade = view.findViewById<TextView>(R.id.grade_value)
        val textViewWhyGrade = view.findViewById<TextView>(R.id.why_grade)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_one_grade, parent, false)
        return GradesHolder(view)
    }

    override fun onBindViewHolder(holder: GradesHolder, position: Int) {
        holder.textViewGrade.text = grades.value?.get(position)?.grade
        holder.textViewWhyGrade.text=grades.value?.get(position)?.why_grade
        holder.myView.setOnClickListener(){
            viewModel.gradeValue = grades.value?.get(position)?.grade
            viewModel.whyGrade = grades.value?.get(position)?.why_grade
            viewModel.grade = Grades(grades.value?.get(position)?.gradeID!!, grades.value?.get(position)?.student_id!! ,grades.value?.get(position)?.grade!! ,grades.value?.get(position)?.why_grade!!)
        }
    }

    override fun getItemCount()=grades.value?.size?:0
}