package com.example.teacherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.ViewModel.adapters.GroupsAdapter
import com.example.teacherapp.ViewModel.adapters.SubjectsAdapter
import com.example.teacherapp.ViewModel.factories.GroupsHandler
import com.example.teacherapp.ViewModel.factories.GroupsHandlerFactory
import com.example.teacherapp.ViewModel.factories.SubjectsHandler
import com.example.teacherapp.ViewModel.factories.SubjectsHandlerFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [groups_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class groups_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelSubjects: SubjectsHandler
    private lateinit var viewModelGroups: GroupsHandler

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
        return inflater.inflate(R.layout.groups_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryList = GroupsHandlerFactory((requireNotNull(this.activity).application))
        viewModelGroups=ViewModelProvider(requireActivity(),factoryList).get(GroupsHandler::class.java)
        val groupsAdapter= GroupsAdapter(viewModelGroups.groups,viewModelGroups)
        viewModelGroups.groups.observe(viewLifecycleOwner,{groupsAdapter.notifyDataSetChanged()})

        val factorySubject = SubjectsHandlerFactory((requireNotNull(this.activity).application))
        viewModelSubjects = ViewModelProvider(requireActivity(), factorySubject).get(SubjectsHandler::class.java)
        view.findViewById<TextView>(R.id.one_subject_name).text = viewModelSubjects.subjectName

        val layoutManager= LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.groupsRecycleView).let {
            it.adapter=groupsAdapter
            it.layoutManager=layoutManager
        }

        view.findViewById<Button>(R.id.button_delete_subject).apply{
            setOnClickListener {
                viewModelSubjects.DeleteSubject(viewModelSubjects.subject)
                view.findNavController().navigate(R.id.action_groups_fragment_to_subjects_fragment)
            }
        }

        view.findViewById<Button>(R.id.button_create_group).apply {
            setOnClickListener{
                view.findNavController().navigate(R.id.action_groups_fragment_to_add_group_fragment2)
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
         * @return A new instance of fragment groups_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            groups_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}