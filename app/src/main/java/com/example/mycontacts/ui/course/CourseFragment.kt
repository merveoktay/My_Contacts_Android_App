package com.example.mycontacts.ui.course

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import com.example.mycontacts.ui.ActivityViewModel
import com.example.mycontacts.ui.ContactsDetail
import com.example.mycontacts.MainActivity.Companion.card
import com.example.mycontacts.adapters.CustomAdapter
import com.example.mycontacts.databinding.FragmentCourseBinding
import com.example.mycontacts.model.ContactCard

class CourseFragment : Fragment() {


    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!
    private val activityViewModel: ActivityViewModel by activityViewModels()

    var contactCard=mutableListOf<ContactCard>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.listViewCourse
        val editText: EditText =binding.editTextCourseSearch
        val buttonSearch: Button =binding.buttonSearch

        activityViewModel.searchGroup("Course")
        activityViewModel.arrGroup.observe(viewLifecycleOwner ){
            for (item in it) {
                contactCard.add(item)
                Log.d("status",item.toString())
            }
            listView.adapter= CustomAdapter(requireActivity(),it)
        }

        buttonSearch.setOnClickListener {
            activityViewModel.searchWithName(editText.text.toString())
            activityViewModel.searchName.observe(viewLifecycleOwner) {
                for (item in it) {
                    Log.d("status", item.toString())
                }
                listView.adapter = CustomAdapter(requireActivity(), it)
            }
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            card= contactCard[position]
            val intent = Intent(activity, ContactsDetail::class.java)
            startActivity(intent)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}