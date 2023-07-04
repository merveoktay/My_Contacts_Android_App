package com.example.mycontacts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycontacts.MainActivity
import com.example.mycontacts.model.ContactCard


class ActivityViewModel: ViewModel() {
    private val database = MainActivity.db.contactCardDao()

    private val _arr = MutableLiveData<List<ContactCard>>().apply {
        val run= Runnable {
            postValue(database.getTopTen())
        }
        Thread(run).start()
    }
    val arr: LiveData<List<ContactCard>> = _arr

    private val _arrGroup = MutableLiveData<List<ContactCard>>()
    val arrGroup: LiveData<List<ContactCard>> = _arrGroup
    fun searchGroup(group: String) {
        val run = Runnable {
            _arrGroup.postValue(database.searchGroup(group))
        }
        Thread(run).start()
    }

    private val _searchName = MutableLiveData<List<ContactCard>>()
    val searchName: LiveData<List<ContactCard>> = _searchName
    fun searchWithName(name: String) {
        val run = Runnable {
            _searchName.postValue(database.searchName(name))
        }
        Thread(run).start()
    }
}
