package com.example.teacherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherapp.ViewModel.Subjects_Handler
import com.example.teacherapp.ViewModel.adapters.Subjects_adapter
import com.example.teacherapp.ViewModel.factories.Subjects_Factory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [subjects_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class subjects_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModelList:Subjects_Handler


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
        return inflater.inflate(R.layout.subjects_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factoryList = Subjects_Factory((requireNotNull(this.activity).application))
        viewModelList=ViewModelProvider(requireActivity(),factoryList).get(Subjects_Handler::class.java)
        val classListAdapter= Subjects_adapter(viewModelList.subjects,viewModelList)
        viewModelList.subjects.observe(viewLifecycleOwner,{classListAdapter.notifyDataSetChanged()})

        val layoutManager=LinearLayoutManager(view.context)

        view.findViewById<RecyclerView>(R.id.subjectsRecycleView).let {
            it.adapter=classListAdapter
        it.layoutManager=layoutManager
        }

        view.findViewById<Button>(R.id.button_add_subject).apply {
            setOnClickListener{
                view.findNavController().navigate(R.id.action_subjects_fragment_to_add_subject_fragment)
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
         * @return A new instance of fragment subjects_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            subjects_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}