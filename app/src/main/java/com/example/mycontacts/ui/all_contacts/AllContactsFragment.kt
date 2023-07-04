package com.example.mycontacts.ui.all_contacts


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mycontacts.ui.ActivityViewModel
import com.example.mycontacts.ui.ContactsDetail
import com.example.mycontacts.MainActivity
import com.example.mycontacts.adapters.CustomAdapter
import com.example.mycontacts.databinding.FragmentAllContactsBinding
import com.example.mycontacts.model.ContactCard


class AllContactsFragment : Fragment() {

    private var _binding: FragmentAllContactsBinding? = null
    private val binding get() = _binding!!
    private val activityViewModel: ActivityViewModel by activityViewModels()
    var contactCard=mutableListOf<ContactCard>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView: ListView = binding.listViewAll
        val editText: EditText =binding.editTextAllSearch
        val buttonSearch:Button=binding.buttonSearch

        activityViewModel.searchWithName(editText.text.toString())


        activityViewModel.arr.observe(viewLifecycleOwner ){
            for (item in it) {
                contactCard.add(item)
                Log.d("status",item.toString())
            }
            listView.adapter= CustomAdapter(requireActivity(),it)
        }

        buttonSearch.setOnClickListener {
            activityViewModel.searchName.observe(viewLifecycleOwner) {
                for (item in it) {
                    Log.d("status", item.toString())
                }
                listView.adapter = CustomAdapter(requireActivity(), it)
            }
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            MainActivity.card = contactCard[position]
            val intent = Intent(activity, ContactsDetail::class.java)
            startActivity(intent)

        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}