package com.example.mycontacts.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycontacts.dao.ContactCardDao
import com.example.mycontacts.model.ContactCard

@Database(entities = [ContactCard::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactCardDao() : ContactCardDao

}