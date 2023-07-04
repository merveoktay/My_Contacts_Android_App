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
import com.example.mycontacts.MainActivity.Companion.card
import com.example.mycontacts.R
import com.example.mycontacts.configs.Database
import com.example.mycontacts.model.ContactCard
import com.google.android.material.snackbar.Snackbar
import kotlin.concurrent.fixedRateTimer

class ContactsDetail : AppCompatActivity() {
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button
    private lateinit var editTextDetailName: EditText
    private lateinit var editTextDetailSurname: EditText
    private lateinit var editTextDetailPhone: EditText
    private lateinit var editTextDetailAddress: EditText
    private lateinit var editTextDetailMail: EditText
    private lateinit var editTextDetailGroup:EditText

    private lateinit var group:String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_detail)

      init()

    }
    private val buttonUpdateOnClickListener= View.OnClickListener {
        val dt= Database.datebase(applicationContext)
        val db = dt.contactCardDao()

        if (editTextDetailName.text.toString() != "" && editTextDetailSurname.text.toString() != "" && editTextDetailPhone.text.toString() != "" && group != "") {
            val run = Runnable {
                val contact = ContactCard(
                    card.id, editTextDetailName.text.toString(),
                    editTextDetailSurname.text.toString(),
                    editTextDetailPhone.text.toString(),
                    editTextDetailAddress.text.toString(),
                    editTextDetailMail.text.toString(),
                    editTextDetailGroup.text.toString()
                )
                db.update(contact)


                val ls = db.getAll()
                Log.d("item",ls.toString() )

            }
            Thread(run).start()
            Snackbar.make(it, "Update was successful", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
                val timer = fixedRateTimer("Tag", false, 1000, 2500){
                finish()
            }
            val intent = Intent (this , MainActivity::class.java )
            startActivity(intent)
        }
        else
        {
            Toast.makeText(this,"Please enter the  information ! ", Toast.LENGTH_LONG).show()
        }

    }
    private val buttonDeleteOnClickListener=View.OnClickListener {
        val dt= Database.datebase(applicationContext)
        val db = dt.contactCardDao()

        val run = Runnable {
            val contact = ContactCard(
                card.id, editTextDetailName.text.toString(),
                editTextDetailSurname.text.toString(),
                editTextDetailPhone.text.toString(),
                editTextDetailAddress.text.toString(),
                editTextDetailMail.text.toString(),
                editTextDetailGroup.text.toString()
            )
            db.delete(contact)
        }
        Thread(run).start()
        Snackbar.make(it, "Deletion was successful", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
           val timer = fixedRateTimer("Tag", false, 1000, 2500){
            finish()
        }
        val intent = Intent (this , MainActivity::class.java )
        startActivity(intent)
    }

    private val editTextUpdateGroupOnClickListener = View.OnClickListener {
        val popupMenu= PopupMenu(this,editTextDetailGroup)
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
        editTextDetailGroup.setText(group)
        popupMenu.show()
    }

   private fun init(){
        buttonUpdate=findViewById(R.id.buttonUpdate)
        buttonDelete=findViewById(R.id.buttonDelete)
        editTextDetailName=findViewById(R.id.editTextDetailName)
        editTextDetailSurname=findViewById(R.id.editTextDetailSurname)
        editTextDetailPhone=findViewById(R.id.editTextDetailPhone)
        editTextDetailAddress=findViewById(R.id.editTextDetailMail)
        editTextDetailMail=findViewById(R.id.editTextDetailAddress)
        editTextDetailGroup=findViewById(R.id.editTextDetailGroup)

        editTextDetailName.setText(card.name)
        editTextDetailSurname.setText(card.surname)
        editTextDetailPhone.setText(card.phoneNumber)
        editTextDetailMail.setText(card.mail)
        editTextDetailAddress.setText(card.address)
        editTextDetailGroup.setText(card.group)
        group= card.group

        editTextDetailGroup.setOnClickListener(editTextUpdateGroupOnClickListener)
        buttonUpdate.setOnClickListener(buttonUpdateOnClickListener)
        buttonDelete.setOnClickListener(buttonDeleteOnClickListener)
    }
}