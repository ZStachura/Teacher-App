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
import com.example.teacherapp.ViewModel.adapters.GradesAdapter
import com.example.teacherapp.ViewModel.factories.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [student_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class student_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelStudents: StudentsHandler
    private lateinit var viewModelGrades :GradesHandler

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
        return inflater.inflate(R.layout.student_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryStudent = StudentsHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudents = ViewModelProvider(requireActivity(), factoryStudent).get(StudentsHandler::class.java)
        view.findViewById<TextView>(R.id.one_student_surname).text = viewModelStudents.studentSurname
        view.findViewById<TextView>(R.id.subject_grade).text = viewModelStudents.studentName
        view.findViewById<TextView>(R.id.subject_name_grade).text = viewModelStudents.album

        val factoryGrade = GradesHandlerFactory((requireNotNull(this.activity).application))
        viewModelGrades=ViewModelProvider(requireActivity(),factoryGrade).get(GradesHandler::class.java)
        val gradesAdapter= GradesAdapter(viewModelGrades.GetThatGrades(viewModelStudents.student.studentID),viewModelGrades)
        viewModelGrades.currentgrades.observe(viewLifecycleOwner,{gradesAdapter.notifyDataSetChanged()})

        val layoutManager= LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.gradesRecycleView).let {
            it.adapter=gradesAdapter
            it.layoutManager=layoutManager
        }

        view.findViewById<Button>(R.id.button_delete_student).apply{
            setOnClickListener {
                viewModelStudents.DeleteStudent(viewModelStudents.student)
                view.findNavController().navigate(R.id.action_student_fragment_to_onegroup_fragment)
            }
        }

        view.findViewById<Button>(R.id.button_create_grade).apply {
            setOnClickListener{
                view.findNavController().navigate(R.id.action_student_fragment_to_add_grade_fragment)
            }
        }

        view.findViewById<Button>(R.id.button_back_onestudent).apply{
            setOnClickListener{
                view.findNavController().navigate(R.id.action_student_fragment_to_onegroup_fragment)
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
         * @return A new instance of fragment student_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            student_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}