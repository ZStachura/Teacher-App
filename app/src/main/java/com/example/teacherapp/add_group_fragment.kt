package com.example.teacherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherapp.ViewModel.factories.GroupsHandler
import com.example.teacherapp.ViewModel.factories.GroupsHandlerFactory
import com.example.teacherapp.ViewModel.factories.SubjectsHandler
import com.example.teacherapp.ViewModel.factories.SubjectsHandlerFactory
import com.example.teacherapp.entities.Groups
import com.example.teacherapp.entities.Subjects

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [add_group_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class add_group_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelAdd: GroupsHandler
    private lateinit var subjectView: SubjectsHandler

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
        return inflater.inflate(R.layout.add_group_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dayspinner: Spinner=view.findViewById<Spinner>(R.id.group_day_add)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.days,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            dayspinner.adapter=adapter
        }

        val startspinner: Spinner=view.findViewById<Spinner>(R.id.start_hour_add)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.hours,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            startspinner.adapter=adapter
        }

        val stopspinner: Spinner=view.findViewById<Spinner>(R.id.end_hour_add)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.hours,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            stopspinner.adapter=adapter
        }

        val factorySubject = SubjectsHandlerFactory((requireNotNull(this.activity).application))
        subjectView = ViewModelProvider(requireActivity(), factorySubject).get(SubjectsHandler::class.java)

        val factoryAdd = GroupsHandlerFactory((requireNotNull(this.activity).application))
        viewModelAdd= ViewModelProvider(requireActivity(),factoryAdd).get(GroupsHandler::class.java)
        view.findViewById<Button>(R.id.button_add_group).apply {
            setOnClickListener{
                val group= Groups(0,view.findViewById<EditText>(R.id.group_name_add).text.toString(),dayspinner.selectedItem.toString(), startspinner.selectedItem.toString(),stopspinner.selectedItem.toString(), subjectView.subject.classID)
                viewModelAdd.AddGroup(group)
                view.findNavController().navigate(R.id.action_add_group_fragment_to_groups_fragment2)
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
         * @return A new instance of fragment add_group_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            add_group_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}