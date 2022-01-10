package com.example.teacherapp.ViewModel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.R
import com.example.teacherapp.ViewModel.factories.GroupsHandler
import com.example.teacherapp.ViewModel.factories.SubjectsHandler
import com.example.teacherapp.entities.Groups
import com.example.teacherapp.entities.Subjects

class GroupsAdapter(private val groups: LiveData<List<Groups>>, private val viewModel: GroupsHandler): RecyclerView.Adapter<GroupsAdapter.GroupsHolder>() {

    inner class GroupsHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textViewName = view.findViewById<TextView>(R.id.one_group_name)
        val myView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_fragmet_one_group, parent, false)
        return GroupsHolder(view)
    }

    override fun onBindViewHolder(holder: GroupsHolder, position: Int) {
        holder.textViewName.text = groups.value?.get(position)?.groupName
        holder.myView.setOnClickListener(){
            viewModel.groupName = groups.value?.get(position)?.groupName
            viewModel.day=groups.value?.get(position)?.day
            viewModel.start=groups.value?.get(position)?.start
            viewModel.end=groups.value?.get(position)?.end
            viewModel.group = Groups(groups.value?.get(position)?.groupID!!, groups.value?.get(position)?.groupName!!,groups.value?.get(position)?.day!!,groups.value?.get(position)?.start!!,groups.value?.get(position)?.end!!,groups.value?.get(position)?.subjectID!!)
            holder.myView.findNavController().navigate(R.id.action_groups_fragment_to_onegroup_fragment)
        }
    }

    override fun getItemCount()=groups.value?.size?:0
}