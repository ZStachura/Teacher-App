package com.example.teacherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.ViewModel.adapters.StudentsAdapter
import com.example.teacherapp.ViewModel.adapters.SubjectsAdapter
import com.example.teacherapp.ViewModel.factories.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [onegroup_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class onegroup_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelGroups: GroupsHandler
    private lateinit var viewModelStudents: StudentsHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.onegroup_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryGroup = GroupsHandlerFactory((requireNotNull(this.activity).application))
        viewModelGroups = ViewModelProvider(requireActivity(), factoryGroup).get(GroupsHandler::class.java)
        view.findViewById<TextView>(R.id.group_name).text = viewModelGroups.groupName
        view.findViewById<TextView>(R.id.day_view).text=viewModelGroups.day
        view.findViewById<TextView>(R.id.start_hour_view).text=viewModelGroups.start
        view.findViewById<TextView>(R.id.end_hour_view).text=viewModelGroups.end

        val factoryStudent = StudentsHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents=ViewModelProvider(requireActivity(),factoryStudent).get(StudentsHandler::class.java)
        val studentsAdapter= StudentsAdapter(viewModelStudents.GetThatStudents(viewModelGroups.group.groupID),viewModelStudents)
        viewModelStudents.currentstudents.observe(viewLifecycleOwner,{studentsAdapter.notifyDataSetChanged()})

        val layoutManager= LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.studentsRecycleView).let {
            it.adapter=studentsAdapter
            it.layoutManager=layoutManager
        }


        view.findViewById<Button>(R.id.button_delete_group).apply{
            setOnClickListener {
                viewModelGroups.DeleteGroup(viewModelGroups.group)
                view.findNavController().navigate(R.id.action_onegroup_fragment_to_groups_fragment)
            }
        }

        view.findViewById<Button>(R.id.button_create_student).apply {
            setOnClickListener{
                view.findNavController().navigate(R.id.action_onegroup_fragment_to_add_student_fragment)
            }
        }

        view.findViewById<Button>(R.id.button_back_group).apply{
            setOnClickListener{
                view.findNavController().navigate(R.id.action_onegroup_fragment_to_groups_fragment)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment onegroup_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            onegroup_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}