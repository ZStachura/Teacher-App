package com.example.teacherapp.ViewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.R
import com.example.teacherapp.ViewModel.Subjects_Handler
import com.example.teacherapp.entities.Subjects


class Subjects_adapter(private val subjects: LiveData<List<Subjects>>, private val viewModel: Subjects_Handler): RecyclerView.Adapter<Subjects_adapter.SubjectsListHolder>() {

    inner class SubjectsListHolder(private val view: View):RecyclerView.ViewHolder(view)
    {
        val textViewId=view.findViewById<TextView>(R.id.subjectsid)
        val textViewName=view.findViewById<TextView>(R.id.subjectsname)
        val myView=view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsListHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.one_lecture_row,parent,false)
        return SubjectsListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectsListHolder, position: Int) {
        holder.textViewId.text=subjects.value?.get(position)?.classID.toString()
        holder.textViewName.text=subjects.value?.get(position)?.className
        holder.myView.setOnClickListener(){
            viewModel.subjectsname=subjects.value?.get(position)?.className
            viewModel.subjects=Subjects(subjects.value?.get(position)?.classID!!, subjects.value?.get(position)?.className!!)
            holder.myView.findNavController().navigate(R.id.action_add_subject_fragment_to_subjects_fragment)
        }
    }

    override fun getItemCount()=subjects.value?.size?:0
    }

}