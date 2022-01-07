package com.example.teacherapp.ViewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.R
import com.example.teacherapp.entities.Subjects
import com.example.teacherapp.ViewModel.factories.SubjectsHandler

class SubjectsAdapter(private val subjects: LiveData<List<Subjects>>, private val viewModel: SubjectsHandler): RecyclerView.Adapter<SubjectsAdapter.SubjectsHolder>() {
    inner class SubjectsHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val textViewName = view.findViewById<TextView>(R.id.subjectName)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_one_subject, parent, false)
        return SubjectsHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectsHolder, position: Int) {
        holder.textViewName.text = subjects.value?.get(position)?.className
        holder.myView.setOnClickListener(){
            viewModel.subjectName = subjects.value?.get(position)?.className
            viewModel.subject = Subjects(subjects.value?.get(position)?.classID!!, subjects.value?.get(position)?.className!!)
            holder.myView.findNavController().navigate(R.id.action_subjects_fragment_to_groups_fragment)
        }
    }

    override fun getItemCount()=subjects.value?.size?:0
}