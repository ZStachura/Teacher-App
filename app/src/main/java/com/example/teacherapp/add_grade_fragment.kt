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
import com.example.teacherapp.ViewModel.factories.*
import com.example.teacherapp.entities.Grades
import com.example.teacherapp.entities.Students

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [add_grade_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class add_grade_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelStudent: StudentsHandler
    private lateinit var viewModelAdd: GradesHandler

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
        return inflater.inflate(R.layout.add_grade_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gradespinner: Spinner =view.findViewById<Spinner>(R.id.grade_add)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.choosegrade,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            gradespinner.adapter=adapter
        }

        val factoryStudent = StudentsHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudent = ViewModelProvider(requireActivity(), factoryStudent).get(StudentsHandler::class.java)

        val factoryAdd = GradesHandlerFactory((requireNotNull(this.activity).application))
        viewModelAdd= ViewModelProvider(requireActivity(),factoryAdd).get(GradesHandler::class.java)
        view.findViewById<Button>(R.id.button_add_grade).apply {
            setOnClickListener{
                val grade= Grades(0,viewModelStudent.student.studentID,gradespinner.selectedItem.toString(),view.findViewById<EditText>(R.id.why_grade).text.toString())
                viewModelAdd.AddGrade(grade)
                view.findNavController().navigate(R.id.action_add_grade_fragment_to_student_fragment)
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
         * @return A new instance of fragment add_grade_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            add_grade_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}