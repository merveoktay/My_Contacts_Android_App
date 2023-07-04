package com.example.mycontacts.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mycontacts.R
import com.example.mycontacts.model.ContactCard

class CustomAdapter(private val context: Activity, private val contact: List<ContactCard>) : ArrayAdapter<ContactCard>(context, R.layout.custom_list_item, contact ) {

    @SuppressLint("ViewHolder", "SetTextI18n", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.custom_list_item, null, true)

        val listItemNameSurname = rootView.findViewById<TextView>(R.id.listItemNameSurname)
        val listItemPhone = rootView.findViewById<TextView>(R.id.listemItemPhone)
        val listItemMail = rootView.findViewById<TextView>(R.id.listItemMail)
        val listItemAddress = rootView.findViewById<TextView>(R.id.listItemAddress)
        val listItemGroup = rootView.findViewById<TextView>(R.id.listItemGroup)
        val contactCard = contact[position]

        listItemNameSurname.text="${contactCard.name} ${contactCard.surname}"
        listItemPhone.text=contactCard.phoneNumber.toString()
        listItemMail.text=contactCard.mail
        listItemAddress.text=contactCard.address
        listItemGroup.text=contactCard.group

        return rootView
    }
}