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
 * Use the [Grade_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Grade_details : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelGrades : GradesHandler
    private lateinit var viewModelStudent: StudentsHandler

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
        return inflater.inflate(R.layout.fragment_grade_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryStudent = StudentsHandlerFactory((requireNotNull(this.activity).application))
        viewModelStudent = ViewModelProvider(requireActivity(), factoryStudent).get(StudentsHandler::class.java)

        val factoryGrade = GradesHandlerFactory((requireNotNull(this.activity).application))
        viewModelGrades= ViewModelProvider(requireActivity(),factoryGrade).get(GradesHandler::class.java)

        view.findViewById<TextView>(R.id.student_name_grade).text = viewModelStudent.studentSurname
        view.findViewById<TextView>(R.id.grade_value_grade).text = viewModelGrades.gradeValue
        view.findViewById<TextView>(R.id.task_name_grade).text = viewModelGrades.whyGrade

        view.findViewById<Button>(R.id.button_delete_grade).apply{
            setOnClickListener {
                viewModelGrades.DeleteGrade(viewModelGrades.grade)
                view.findNavController().navigate(R.id.action_grade_details_to_student_fragment)
            }
        }

        view.findViewById<Button>(R.id.button_back_grade).apply{
            setOnClickListener{
                view.findNavController().navigate(R.id.action_grade_details_to_student_fragment)
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
         * @return A new instance of fragment Grade_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Grade_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}