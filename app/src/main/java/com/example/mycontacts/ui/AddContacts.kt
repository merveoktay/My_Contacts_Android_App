package com.example.mycontacts.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import com.example.mycontacts.MainActivity
import com.example.mycontacts.R
import com.example.mycontacts.configs.Database
import com.example.mycontacts.model.ContactCard
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.fixedRateTimer

class AddContacts : AppCompatActivity() {

    private lateinit var buttonAddGroup:Button
    private lateinit var buttonAdd:Button
    private lateinit var editTextAddName:EditText
    private lateinit var editTextAddSurname:EditText
    private lateinit var editTextAddPhone:EditText
    private lateinit var editTextAddAddress:EditText
    private lateinit var editTextAddMail:EditText
    private lateinit var group:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contacts)
        init()
    }

    private val buttonAddOnClickListener=View.OnClickListener {
        val dt= Database.datebase(applicationContext)
        val db = dt.contactCardDao()
        val array=mutableListOf<ContactCard>()
        if(editTextAddName.text.toString() != ""&& editTextAddSurname.text.toString()!=""&& editTextAddPhone.text.toString()!=""&&group!="") {
           val run = Runnable {
               val contact = ContactCard(
                   null,
                   editTextAddName.text.toString(),
                   editTextAddSurname.text.toString(),
                   editTextAddPhone.text.toString(),
                   editTextAddAddress.text.toString(),
                   editTextAddMail.text.toString(),
                   group
               )
               val status = db.insert(contact)
               Log.d("Status", status.toString())

           }
           Thread(run).start()
           Snackbar.make(it, "Adding was successful", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show()
             val timer = fixedRateTimer("Tag", false, 1000, 2500){
               finish()
             }
            val intent = Intent (this , MainActivity::class.java )

            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Please enter the  information ! ", Toast.LENGTH_LONG).show()
       }
    }
    private val buttonAddGroupOnClickListener = View.OnClickListener {
        val popupMenu= PopupMenu(this,buttonAddGroup)
        popupMenu.menuInflater.inflate(R.menu.groupmenu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.group_family -> {
                    group="Family"
                }
                R.id.group_friends -> {
                    group="Friends"
                }
                R.id.group_school -> {
                    group="School"
                }
                R.id.group_work -> {
                    group="Work"
                }
                R.id.group_course -> {
                    group="Course"
                }
            }
            true
        })
        popupMenu.show()
    }
    private fun init(){
        buttonAdd=findViewById(R.id.buttonUpdate)
        buttonAddGroup=findViewById(R.id.buttonAddGroup)
        editTextAddName=findViewById(R.id.editTextDetailName)
        editTextAddSurname=findViewById(R.id.editTextDetailSurname)
        editTextAddPhone=findViewById(R.id.editTextDetailPhone)
        editTextAddMail=findViewById(R.id.editTextDetailMail)
        editTextAddAddress=findViewById(R.id.editTextDetailAddress)

        buttonAddGroup.setOnClickListener(buttonAddGroupOnClickListener)
        buttonAdd.setOnClickListener(buttonAddOnClickListener)
    }

}