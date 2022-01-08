package com.example.teacherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherapp.ViewModel.factories.StudentsHandler
import com.example.teacherapp.ViewModel.factories.StudentsHandlerFactory
import com.example.teacherapp.ViewModel.factories.SubjectsHandler
import com.example.teacherapp.ViewModel.factories.SubjectsHandlerFactory
import com.example.teacherapp.entities.Students
import com.example.teacherapp.entities.Subjects

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [add_student_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class add_student_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelAdd: StudentsHandler

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
        return inflater.inflate(R.layout.add_student_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryAdd = StudentsHandlerFactory((requireNotNull(this.activity).application))
        viewModelAdd= ViewModelProvider(requireActivity(),factoryAdd).get(StudentsHandler::class.java)
        view.findViewById<Button>(R.id.button_add_student).apply {
            setOnClickListener{
                val student= Students(0,view.findViewById<EditText>(R.id.student_id_add).text.toString(),view.findViewById<EditText>(R.id.student_name_add).text.toString(),view.findViewById<EditText>(R.id.student_surname_add).text.toString(),0)
                viewModelAdd.AddStudent(student)
                view.findNavController().navigate(R.id.action_add_student_fragment_to_onegroup_fragment)
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
         * @return A new instance of fragment add_student_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            add_student_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}